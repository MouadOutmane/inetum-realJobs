package world.inetum.realdolmen.realjobs.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import world.inetum.realdolmen.realjobs.BaseIntegrationTest;
import world.inetum.realdolmen.realjobs.InetumRealJobsApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest(classes = InetumRealJobsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfileControllerIT extends BaseIntegrationTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void viewProfile_userExists() throws Exception {
        String username = "user@user.user";
        persistJobSeeker(username, "password");
        mockMvc.perform(get("/api/profile/" + username))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.gender").exists(),
                        jsonPath("$.gender", Matchers.is("MALE")),
                        jsonPath("$.email").exists(),
                        jsonPath("$.email", Matchers.is(username))
                );

    }

    @Test
    public void viewProfile_userDoesNotExist() throws Exception {
        String username = "user@user";
        mockMvc.perform(get("/api/profile/" + username))
                .andExpect(status().isBadRequest()).
                andExpectAll(
                        jsonPath("$.message").exists(),
                        jsonPath("$.message", Matchers.is("PROFILE_NOT_FOUND"))
                );
    }


}
