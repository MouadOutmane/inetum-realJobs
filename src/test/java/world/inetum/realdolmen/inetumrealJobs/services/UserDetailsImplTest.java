package world.inetum.realdolmen.inetumrealJobs.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import world.inetum.realdolmen.inetumrealJobs.entities.ERole;
import world.inetum.realdolmen.inetumrealJobs.entities.User;

class UserDetailsImplTest {


    @Test
    void when_BuildingUserDetailImplementationObject_VerifyAllProperties() {
        User user = new User("user", "pword");

        user.setRole(ERole.ROLE_JOB_SEEKER);

        UserDetailsImpl userDetails = UserDetailsImpl.build(user);

        Assertions.assertEquals("user", userDetails.getUsername());
        Assertions.assertEquals(ERole.ROLE_JOB_SEEKER.toString(),
                userDetails.getAuthorities().stream().findFirst().get().getAuthority());

    }


}
