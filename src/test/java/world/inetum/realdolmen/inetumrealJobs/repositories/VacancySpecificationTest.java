package world.inetum.realdolmen.inetumrealJobs.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import world.inetum.realdolmen.inetumrealJobs.entities.old.Vacancy;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import world.inetum.realdolmen.inetumrealJobs.entities.Vacancy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.jpa.domain.Specification.where;
import static world.inetum.realdolmen.inetumrealJobs.repositories.VacancySpecification.*;


@DataJpaTest
public class VacancySpecificationTest {


    @Autowired
    VacancyRepository vacancyRepository;

    @BeforeEach
    void setUp(){
        vacancyRepository.save(new Vacancy("junior java consultant", "full-time", "java programming", "Inetum", "IT", "Italy", "Rome", 10122, "avenue de la liberte",  33,  0, "1k/month"));
        vacancyRepository.save(new Vacancy("senior java consultant", "full-time", "java programming", "Realdolmen", "IT", "Italy", "Milan", 10123, "avenue de l'espoir",  34,  3, "2k/month"));
        vacancyRepository.save(new Vacancy("burger flipper", "full-time", "flipping burgers", "McDonald's", "IT", "USA", "Washington", 10124, "avenue de la force",  35,  3, "3k/month"));

    }

    @Test
    void findAll_FullTimeItaly3YearsOfExperienceVacancies_True(){
        String contractType= "Full-Time";
        String country = "italy";
        Integer requiredYearsOfExperience = 3;
        String industry = null;

        List<Vacancy> results = vacancyRepository.findAll(where(withContractType(contractType))
                .and(withCountry(country))
                .and(withRequiredYearsOfExperience(requiredYearsOfExperience))
                .and(withIndustry(industry)));

        assertEquals(2, results.size());
        assertEquals("Italy", results.get(0).getCountry());

    }

    @Test
    void testWithNullIndustry(){
        List<Vacancy> results = vacancyRepository.findAll(where(withIndustry(null)));
        assertFalse(results.isEmpty());
    }

    @Test
    void testWithNullCountry(){
        List<Vacancy> results = vacancyRepository.findAll(where(withCountry(null)));
        assertFalse(results.isEmpty());
    }

    @Test
    void testWithNullFunctionTitle(){
        List<Vacancy> results = vacancyRepository.findAll(where(withFunctionTitle(null)));
        assertFalse(results.isEmpty());
    }

    @Test
    void testWithNullContractType(){
        List<Vacancy> results = vacancyRepository.findAll(where(withContractType(null)));
        assertFalse(results.isEmpty());
    }

    @Test
    void testWithIndustry(){
        List<Vacancy> results = vacancyRepository.findAll(where(withIndustry("IT")));
        assertEquals("IT", results.get(0).getIndustry());
    }

    @Test
    void testWithCountry(){
        List<Vacancy> results = vacancyRepository.findAll(where(withCountry("italy")));
        assertEquals("Italy", results.get(0).getCountry());
    }

    @Test
    void testWithContractType(){
        List<Vacancy> results = vacancyRepository.findAll(where(withContractType("full-time")));
        assertEquals("Full-Time", results.get(0).getContractType());
    }

    @Test
    void testWithFunctionTitle(){
        List<Vacancy> results = vacancyRepository.findAll(where(withFunctionTitle("junior java consultant")));
        assertEquals("junior java consultant", results.get(0).getFunctionTitle() );
    }



}
