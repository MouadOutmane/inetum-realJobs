//package world.inetum.realdolmen.inetumrealJobs.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import world.inetum.realdolmen.inetumrealJobs.InetumRealJobsApplication;
//import world.inetum.realdolmen.inetumrealJobs.payload.request.LoginRequest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc
//@SpringBootTest(classes = InetumRealJobsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class AuthenticationControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Autowired
//    private AuthenticationController authenticationController;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    private ObjectMapper mapper;
//
//    @BeforeEach
//    void setUp() {
//        mapper = new ObjectMapper();
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//
//    @Test
//    void loginWithExistingCredentialsReturning200() throws Exception {
//        LoginRequest request = new LoginRequest("user", "password");
//        mockMvc.perform(
//                        post("/authentication/login")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(mapper.writeValueAsString(request))
//                )
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void loginWithExistingCredentialsReturning401() {
//        LoginRequest request = new LoginRequest("user", "p");
//
//        Exception exception = assertThrows(BadCredentialsException.class, () -> authenticationController.login(request));
//
//        assertEquals("Bad credentials", exception.getMessage());
//    }
//}
