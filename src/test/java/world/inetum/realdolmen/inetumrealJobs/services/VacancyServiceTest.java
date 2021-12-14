package world.inetum.realdolmen.inetumrealJobs.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import world.inetum.realdolmen.inetumrealJobs.BaseRepositoryTest;
import world.inetum.realdolmen.inetumrealJobs.entities.Vacancy;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class VacancyServiceTest extends BaseRepositoryTest {

    VacancyService vacancyService;

    @BeforeEach
    void setUp() {
        vacancyService = new VacancyService(vacancyRepository);
    }


    @Test
    public void givenVacancyRepository_whenSaveAndRetreiveEntity_Success() {
        Vacancy vacancy = new Vacancy("Surfer", "full-time", " surf waves", "QSurfers", "Sports", "Brazil", "Rio de Janeiro", 10125, "street name",  36,  3, "4k/month");

        Vacancy addedVacancy = vacancyService.addVacancy(vacancy);

        Optional<Vacancy> foundEntity = vacancyService.findVacancyById(addedVacancy.getId());

        assertTrue(foundEntity.isPresent());
        assertEquals(addedVacancy.getCity(), (foundEntity.orElse(null)).getCity());
        assertEquals(addedVacancy.getIndustry(), (foundEntity.orElse(null)).getIndustry());
        assertEquals(addedVacancy.getContractType(), (foundEntity.orElse(null)).getContractType());
        assertEquals(addedVacancy.getCompanyName(), (foundEntity.orElse(null)).getCompanyName());


    }

    @Test
    public void findVacancyWithFilter_Success() {
        persistVacancy("junior java consultant", "part-time", "IT", "Belgium", 0);
        persistVacancy("senior java consultant", "full-time", "IT", "Italy", 3);

        List<Vacancy> vacancies = vacancyService.findVacancyWithFilter("senior java consultant", "full-time", "Italy", "It", 3);

        assertEquals(1, vacancies.size(), "Expected a single result.");

        Vacancy vacancy = vacancies.get(0);
        assertEquals("full-time", vacancy.getContractType());
        assertEquals("senior java consultant", vacancy.getFunctionTitle());
        assertEquals("Italy", vacancy.getCountry());
        assertEquals("IT", vacancy.getIndustry());
        assertTrue(3 >= vacancy.getRequiredYearsOfExperience());
    }

}
