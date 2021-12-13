package world.inetum.realdolmen.inetumrealJobs.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.WebApplicationContext;
import world.inetum.realdolmen.inetumrealJobs.InetumRealJobsApplication;
import world.inetum.realdolmen.inetumrealJobs.entities.ERole;
import world.inetum.realdolmen.inetumrealJobs.payload.request.LoginRequest;
import world.inetum.realdolmen.inetumrealJobs.payload.request.SignupRequest;
import world.inetum.realdolmen.inetumrealJobs.payload.response.MessageResponse;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = InetumRealJobsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private WebApplicationContext context;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {

        mapper = new ObjectMapper();

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    }

    @Test
    void loginWithExistingCredentialsReturning200() throws Exception {
        LoginRequest request = new LoginRequest("user", "password");
        mockMvc.perform(post("/authentication/login").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))).andExpect(status().isOk());
    }

    @Test
    void loginWithExistingCredentialsReturning401() throws Exception {
        LoginRequest request = new LoginRequest("user", "p");
        Exception exception = assertThrows(BadCredentialsException.class, () -> {
            authenticationController.login(request);
        });
        String expectedMessage = "Bad credentials";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void when_UserRegisterWithRoleSelected_VerifyAcceptanceCriteria() throws Exception {
        SignupRequest request = new SignupRequest("user1", ERole.ROLE_JOB_SEEKER, "male", "reda",
                "saada", new Date(), "rue", "11", "1", "brussels", "1000",
                "belgium", "+3223456789", "", "1234");
        mockMvc.perform(post("/authentication/signUp").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))).andExpect(status().isOk());
    }

    @Test
    void when_UserRegisterWithUsedUsername_VerifyError() throws Exception {
        SignupRequest request = new SignupRequest("user", ERole.ROLE_JOB_SEEKER, "male", "reda",
                "saada", new Date(), "rue", "11", "1", "brussels", "1000",
                "belgium", "+3223456789", "", "password");
        String expectedMessage = "Error: Username is already taken!";
        MessageResponse messageResponse = (MessageResponse) authenticationController.signUp(request).getBody();
        String actualMessage = messageResponse.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}