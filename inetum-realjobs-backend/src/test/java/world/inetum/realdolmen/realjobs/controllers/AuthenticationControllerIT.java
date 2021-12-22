package world.inetum.realdolmen.realjobs.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import world.inetum.realdolmen.realjobs.BaseIntegrationTest;
import world.inetum.realdolmen.realjobs.InetumRealJobsApplication;
import world.inetum.realdolmen.realjobs.entities.Account;
import world.inetum.realdolmen.realjobs.entities.Country;
import world.inetum.realdolmen.realjobs.entities.JobSeeker;
import world.inetum.realdolmen.realjobs.entities.Recruiter;
import world.inetum.realdolmen.realjobs.entities.enums.Gender;
import world.inetum.realdolmen.realjobs.entities.enums.Role;
import world.inetum.realdolmen.realjobs.exceptions.EndpointException;
import world.inetum.realdolmen.realjobs.exceptions.messages.SignUpExceptionMessage;
import world.inetum.realdolmen.realjobs.payload.dtos.AddressReadDto;
import world.inetum.realdolmen.realjobs.payload.security.LoginRequest;
import world.inetum.realdolmen.realjobs.payload.security.SignupRequest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = InetumRealJobsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationControllerIT extends BaseIntegrationTest {

    @Autowired
    private AuthenticationController authenticationController;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    void loginWithExistingCredentialsReturning200() throws Exception {
        Account account = persistJobSeeker("test@inetum-realdolmen.world", "password");

        LoginRequest request = new LoginRequest("test@inetum-realdolmen.world", "password");

        mockMvc.perform(
                        post("/api/authentication/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").isNotEmpty())
                .andExpect(jsonPath("$.tokenType").value("Bearer"))
                .andExpect(jsonPath("$.id").value(account.getId()))
                .andExpect(jsonPath("$.email").value("test@inetum-realdolmen.world"))
                .andExpect(jsonPath("$.roles").isArray())
                .andExpect(jsonPath("$.roles.length()").value(1))
                .andExpect(jsonPath("$.roles[0]").value("ROLE_JOBSEEKER"));
    }

    @Test
    void loginWithExistingCredentialsReturning401() {
        persistJobSeeker("test@inetum-realdolmen.world", "password");

        LoginRequest request = new LoginRequest("test@inetum-realdolmen.world", "p");

        var exception = assertThrows(BadCredentialsException.class, () -> authenticationController.login(request));

        assertEquals("Bad credentials", exception.getMessage());
    }

    @Test
    void registerWithNonExistingCredentialsReturning200() throws Exception {
        Country country = persistCountry("some country");

        AddressReadDto addressDto = new AddressReadDto();
        addressDto.setStreetName("street name");
        addressDto.setHouseNumber("15");
        addressDto.setCity("city");
        addressDto.setCountry(country.getId());
        addressDto.setPostalCode("1000");

        SignupRequest request = new SignupRequest();
        request.setEmail("user2@user.user");
        request.setPassword("password");
        request.setRole(Role.JOB_SEEKER);
        request.setGender(Gender.MALE);
        request.setFirstName("User");
        request.setLastName("User");
        request.setAddress(addressDto);
        request.setBirthDate(LocalDate.of(2021, 12, 10));
        request.setMobilePhone("+32999999999");

        mockMvc.perform(post("/api/authentication/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        Optional<Account> optionalAccount = accountRepository.findByEmail("user2@user.user");
        assertTrue(optionalAccount.isPresent(), "Account was not found in the database");

        Account account = optionalAccount.get();
        assertAll(() -> {
            assertNotEquals("password", account.getPassword(), "Password should not have saved in plain text.");
            assertInstanceOf(JobSeeker.class, account, "Didn't apply correct role");
        });
    }

    @Test
    void registerWithNonExistingRecruiterCredentialsReturning200() throws Exception {
        Country country = persistCountry("some country");

        AddressReadDto addressDto = new AddressReadDto();
        addressDto.setStreetName("street name");
        addressDto.setHouseNumber("15");
        addressDto.setCity("city");
        addressDto.setCountry(country.getId());
        addressDto.setPostalCode("1000");

        SignupRequest request = new SignupRequest();
        request.setEmail("user2@user.user");
        request.setPassword("password");
        request.setRole(Role.RECRUITER);
        request.setGender(Gender.MALE);
        request.setFirstName("User");
        request.setLastName("User");
        request.setAddress(addressDto);
        request.setBirthDate(LocalDate.of(2021, 12, 10));
        request.setMobilePhone("+32999999999");

        mockMvc.perform(post("/api/authentication/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        Optional<Account> optionalAccount = accountRepository.findByEmail("user2@user.user");
        assertTrue(optionalAccount.isPresent(), "Account was not found in the database");

        Account account = optionalAccount.get();
        assertAll(() -> {
            assertNotEquals("password", account.getPassword(), "Password should not have saved in plain text.");
            assertInstanceOf(Recruiter.class, account, "Didn't apply correct role");
        });
    }

    @Test
    void registerWithExistingCredentialsReturning401() {
        persistJobSeeker("user@user.user", "password");
        Country country = persistCountry("some country");

        AddressReadDto addressDto = new AddressReadDto();
        addressDto.setStreetName("street name");
        addressDto.setHouseNumber("15");
        addressDto.setCity("city");
        addressDto.setCountry(country.getId());
        addressDto.setPostalCode("1000");

        SignupRequest request = new SignupRequest();
        request.setEmail("user@user.user");
        request.setPassword("password");
        request.setRole(Role.JOB_SEEKER);
        request.setGender(Gender.MALE);
        request.setFirstName("User");
        request.setLastName("User");
        request.setAddress(addressDto);
        request.setBirthDate(LocalDate.of(2021, 12, 10));
        request.setMobilePhone("+32999999999");

        EndpointException exception = assertThrows(EndpointException.class, () -> authenticationController.signUp(request));
        assertEquals(SignUpExceptionMessage.EMAIL_ALREADY_USED, exception.getExceptionMessage());
    }

    @Test
    void registerWithFakeCountry() {
        AddressReadDto addressDto = new AddressReadDto();
        addressDto.setStreetName("street name");
        addressDto.setHouseNumber("15");
        addressDto.setCity("city");
        addressDto.setCountry(20L);
        addressDto.setPostalCode("1000");

        SignupRequest request = new SignupRequest();
        request.setEmail("user@user.user");
        request.setPassword("password");
        request.setRole(Role.JOB_SEEKER);
        request.setGender(Gender.MALE);
        request.setFirstName("User");
        request.setLastName("User");
        request.setAddress(addressDto);
        request.setBirthDate(LocalDate.of(2021, 12, 10));
        request.setMobilePhone("+32999999999");

        var exception = assertThrows(EndpointException.class, () -> authenticationController.signUp(request));
        assertEquals(SignUpExceptionMessage.INCORRECT_DATA, exception.getExceptionMessage());
    }

}