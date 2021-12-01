package world.inetum.realdolmen.inetumrealJobs.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import world.inetum.realdolmen.inetumrealJobs.entities.Application;
import world.inetum.realdolmen.inetumrealJobs.entities.User;
import world.inetum.realdolmen.inetumrealJobs.entities.Vacancy;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ApplicationRepositoryTest {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VacancyRepository vacancyRepository;

    User user;

    Vacancy vacancy;

    @BeforeEach
    void setUp(){
        vacancy = vacancyRepository.save(new Vacancy("junior java consultant", "full-time", "java programming", "Inetum", "IT", "Italy", "Rome", 10122, "avenue de la liberte",  33,  0, "1k/month"));

        user = new User();
        user.setUsername("mouad");
        user.setPassword("reda");
        user.setEmail("email@email.com");
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

        userRepository.save(user);

        Application application = new Application();

        application.setUser(user);
        application.setVacancy(vacancy);

        applicationRepository.save(application);
    }

    @Test
    void ApplicationCreation(){
        Application application = applicationRepository.getById(1L);

        assertEquals(vacancy.getId(), application.getVacancy().getId());
        assertEquals(user.getId(), application.getUser().getId());
    }


}
