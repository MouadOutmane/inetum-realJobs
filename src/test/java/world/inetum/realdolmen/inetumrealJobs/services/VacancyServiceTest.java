package world.inetum.realdolmen.inetumrealJobs.services;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import world.inetum.realdolmen.inetumrealJobs.jpa.Vacancy;
import world.inetum.realdolmen.inetumrealJobs.repositories.VacancyRepository;
import world.inetum.realdolmen.inetumrealJobs.services.VacancyService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ActiveProfiles("test")
@SpringBootTest
public class VacancyServiceTest {

    VacancyService vacancyService;

    @Autowired
    VacancyRepository vacancyRepository;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = openMocks(this);
        vacancyService = new VacancyService(vacancyRepository);
    }

    @AfterEach
    void tearDown() {
        try {
            closeable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVacancyRepository_whenSaveAndRetreiveEntity_thenOK() {
        Vacancy vacancy = new Vacancy();
        vacancy.setCity("Rio de Janeiro");

        Vacancy Vacancy = vacancyService.addVacancy(vacancy);

        Optional<Vacancy> foundEntity = vacancyService.findVacancyById(Vacancy.getId());

        assertTrue(foundEntity.isPresent());
        assertEquals(Vacancy.getCity(), (foundEntity.orElse(null)).getCity());

//        foundEntity.ifPresent(tmp -> assertEquals(Vacancy.getCity(), (foundEntity.orElse(null)).getCity())); todo clean optional verification

    }
}
