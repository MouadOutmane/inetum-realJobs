package world.inetum.realdolmen.inetumrealJobs.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import world.inetum.realdolmen.inetumrealJobs.entities.Vacancy;
import world.inetum.realdolmen.inetumrealJobs.repositories.VacancyRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
public class VacancyServiceTest {

    VacancyService vacancyService;

    @Autowired
    VacancyRepository vacancyRepository;


    @BeforeEach
    void setUp() {
        vacancyService = new VacancyService(vacancyRepository);
    }


    @Test
    public void givenVacancyRepository_whenSaveAndRetreiveEntity_thenOK() {
        Vacancy vacancy = new Vacancy();
        vacancy.setCity("Rio de Janeiro");

        Vacancy Vacancy = vacancyService.addVacancy(vacancy);

        Optional<Vacancy> foundEntity = vacancyService.findVacancyById(Vacancy.getId());

        assertTrue(foundEntity.isPresent());
        assertEquals(Vacancy.getCity(), (foundEntity.orElse(null)).getCity());


    }

    @Test
    public void findVacancyWithFilterParameters(){
        List<Vacancy> vacancies = vacancyService.findVacancyWithFilter("junior java consultant", "full-time", "Italy", "It", 3);

        assertEquals("Full-Time", vacancies.get(0).getContractType());
        assertEquals("junior java consultant", vacancies.get(0).getFunctionTitle());
        assertEquals("Italy", vacancies.get(0).getCountry());
        assertEquals("IT", vacancies.get(0).getIndustry());
        assertTrue(3 >= vacancies.get(0).getRequiredYearsOfExperience());



    }
}
