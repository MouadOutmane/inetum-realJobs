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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import world.inetum.realdolmen.realjobs.BaseIntegrationTest;
import world.inetum.realdolmen.realjobs.InetumRealJobsApplication;
import world.inetum.realdolmen.realjobs.payload.dtos.AddressReadDto;
import world.inetum.realdolmen.realjobs.entities.enums.Gender;
import world.inetum.realdolmen.realjobs.entities.enums.Role;
import world.inetum.realdolmen.realjobs.exceptions.EndpointException;
import world.inetum.realdolmen.realjobs.exceptions.messages.SignUpExceptionMessage;
import world.inetum.realdolmen.realjobs.payload.security.LoginRequest;
import world.inetum.realdolmen.realjobs.payload.security.SignupRequest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = InetumRealJobsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationControllerIT extends BaseIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private WebApplicationContext context;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void loginWithExistingCredentialsReturning200() throws Exception {
        persistJobSeeker("test@inetum-realdolmen.world", "password");

        LoginRequest request = new LoginRequest("test@inetum-realdolmen.world", "password");

        mockMvc.perform(
                        post("/api/authentication/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request))
                )
                .andExpect(status().isOk());
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

        AddressReadDto addressDto = new AddressReadDto();
        addressDto.setStreetName("street name");
        addressDto.setHouseNumber("15");
        addressDto.setCity("city");
        addressDto.setCountry(20L);
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
    }

    @Test
    void registerWithExistingCredentialsReturning401() {
        persistJobSeeker("user@user.user", "password");

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

        EndpointException exception = assertThrows(EndpointException.class, () -> authenticationController.signUp(request));
        assertEquals(SignUpExceptionMessage.EMAIL_ALREADY_USED, exception.getExceptionMessage());
    }

}