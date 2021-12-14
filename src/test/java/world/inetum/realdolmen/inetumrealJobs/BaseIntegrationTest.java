package world.inetum.realdolmen.inetumrealJobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import world.inetum.realdolmen.inetumrealJobs.entities.User;

import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class BaseIntegrationTest extends BaseRepositoryTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected User persistUser(String email, String password) {
        User user = new User(email, passwordEncoder.encode(password));
        user.setEmail(email);
        user.setGender("male");
        user.setProfilePicture("picture");
        user.setDateOfBirth(new Date(2021, Calendar.DECEMBER, 14));
        user.setLastName("last name");
        user.setNr("nr");
        user.setCountry("country");
        user.setCity("city");
        user.setStreetName("street");
        user.setFirstName("first name");

        return userRepository.save(user);
    }

}
