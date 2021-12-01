package world.inetum.realdolmen.inetumrealJobs.entities;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    @Test
    public void simpleGetterAndSetterTest(){
        User user = new User();

        user.setUsername("user");
        user.setPassword("password");
        user.setGender("male");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setDateOfBirth(new Date());
        user.setStreetName("rue");
        user.setNr("10");
        user.setBox("1");
        user.setCity("brussels");
        user.setPostalCode("1000");
        user.setCountry("belgium");
        user.setMobilePhone("0123456789");
        user.setProfilePicture("picture");

        assertNotNull(user.getUsername());
        assertNotNull(user.getPassword());
        assertNotNull(user.getGender());
        assertNotNull(user.getFirstName());
        assertNotNull(user.getLastName());
        assertNotNull(user.getDateOfBirth());
        assertNotNull(user.getStreetName());
        assertNotNull(user.getNr());
        assertNotNull(user.getBox());
        assertNotNull(user.getCity());
        assertNotNull(user.getPostalCode());
        assertNotNull(user.getCountry());
        assertNotNull(user.getMobilePhone());
        assertNotNull(user.getProfilePicture());
    }
}
