package world.inetum.realdolmen.inetumrealJobs.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import world.inetum.realdolmen.inetumrealJobs.entities.Vacancy;
import world.inetum.realdolmen.inetumrealJobs.repositories.VacancyRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class VacancyServiceTest {

    VacancyService vacancyService;

    @Autowired
    VacancyRepository vacancyRepository;


    @BeforeEach
    void setUp() {

        vacancyService = new VacancyService(vacancyRepository);
        vacancyRepository.save(new Vacancy("junior java consultant", "full-time", "java programming", "Inetum", "IT", "Italy", "Rome", 10122, "avenue de la liberte",  33,  0, "1k/month"));
        vacancyRepository.save(new Vacancy("senior java consultant", "full-time", "java programming", "Realdolmen", "IT", "Italy", "Milan", 10123, "avenue de l'espoir",  34,  3, "2k/month"));
        vacancyRepository.save(new Vacancy("burger flipper", "full-time", "flipping burgers", "McDonald's", "IT", "USA", "Washington", 10124, "avenue de la force",  35,  3, "3k/month"));


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
    public void findVacancyWithFilter_Success(){
        List<Vacancy> vacancies = vacancyService.findVacancyWithFilter("junior java consultant", "full-time", "Italy", "It", 3);

        assertEquals("full-time", vacancies.get(0).getContractType());
        assertEquals("junior java consultant", vacancies.get(0).getFunctionTitle());
        assertEquals("Italy", vacancies.get(0).getCountry());
        assertEquals("IT", vacancies.get(0).getIndustry());
        assertTrue(3 >= vacancies.get(0).getRequiredYearsOfExperience());



    }
}
