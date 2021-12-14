package world.inetum.realdolmen.inetumrealJobs.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import world.inetum.realdolmen.inetumrealJobs.BaseRepositoryTest;
import world.inetum.realdolmen.inetumrealJobs.entities.Vacancy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.data.jpa.domain.Specification.where;
import static world.inetum.realdolmen.inetumrealJobs.repositories.VacancySpecification.*;

@DataJpaTest
public class VacancySpecificationTest extends BaseRepositoryTest {

    @Test
    void findAll_FullTimeItaly3YearsOfExperienceVacancies_True() {
        persistVacancy("function1", "full-time", "IT", "Belgium", 1);
        persistVacancy("function2", "part-time", "IT", "Belgium", 3);
        persistVacancy("function3", "half-time", "Finance", "Italy", 0);
        persistVacancy("function4", "full-time", "IT", "Italy", 0);
        persistVacancy("function5", "full-time", "IT", "Italy", 4);
        persistVacancy("function6", "full-time", "Finance", "Italy", 8);
        persistVacancy("function6", "full-time", "Finance", "Italy", 3);
        persistVacancy("function6", "full-time", "IT", "Italy", 8);
        persistVacancy("function7", "full-time", "Finance", "Belgium", 7);
        persistVacancy("function8", "full-time", "Public", "Germany", 0);

        String contractType = "Full-Time";
        String country = "italy";
        Integer requiredYearsOfExperience = 3;
        String industry = null;

        List<Vacancy> results = vacancyRepository.findAll(where(withContractType(contractType))
                .and(withCountry(country))
                .and(withRequiredYearsOfExperience(requiredYearsOfExperience))
                .and(withIndustry(industry)));

        assertEquals(2, results.size());

        var firstResult = results.get(0);
        assertEquals("Italy", firstResult.getCountry());
    }

    @Test
    void testWithNullIndustry() {
        persistVacancy("function1", "full-time", "IT", "Belgium", 1);
        persistVacancy("function2", "part-time", "IT", "Belgium", 3);
        persistVacancy("function3", "half-time", "Finance", "Italy", 0);
        persistVacancy("function4", "full-time", "Finance", "Belgium", 7);
        persistVacancy("function5", "full-time", "Public", "Germany", 0);

        List<Vacancy> results = vacancyRepository.findAll(where(withIndustry(null)));

        assertEquals(5, results.size());
    }

    @Test
    void testWithNullCountry() {
        persistVacancy("function1", "full-time", "IT", "Belgium", 1);
        persistVacancy("function2", "part-time", "IT", "Belgium", 3);
        persistVacancy("function3", "half-time", "Finance", "Italy", 0);
        persistVacancy("function4", "full-time", "Finance", "Belgium", 7);
        persistVacancy("function5", "full-time", "Public", "Germany", 0);

        List<Vacancy> results = vacancyRepository.findAll(where(withCountry(null)));

        assertEquals(5, results.size());
    }

    @Test
    void testWithNullFunctionTitle() {
        persistVacancy("function1", "full-time", "IT", "Belgium", 1);
        persistVacancy("function2", "part-time", "IT", "Belgium", 3);
        persistVacancy("function3", "half-time", "Finance", "Italy", 0);
        persistVacancy("function4", "full-time", "Finance", "Belgium", 7);
        persistVacancy("function5", "full-time", "Public", "Germany", 0);

        List<Vacancy> results = vacancyRepository.findAll(where(withFunctionTitle(null)));

        assertEquals(5, results.size());
    }

    @Test
    void testWithNullContractType() {
        persistVacancy("function1", "full-time", "IT", "Belgium", 1);
        persistVacancy("function2", "part-time", "IT", "Belgium", 3);
        persistVacancy("function3", "half-time", "Finance", "Italy", 0);
        persistVacancy("function4", "full-time", "Finance", "Belgium", 7);
        persistVacancy("function5", "full-time", "Public", "Germany", 0);

        List<Vacancy> results = vacancyRepository.findAll(where(withContractType(null)));

        assertEquals(5, results.size());
    }

    @Test
    void testWithIndustry() {
        persistVacancy("function1", "full-time", "IT", "Belgium", 1);
        persistVacancy("function2", "part-time", "IT", "Belgium", 3);
        persistVacancy("function3", "half-time", "Finance", "Italy", 0);
        persistVacancy("function4", "full-time", "Finance", "Belgium", 7);
        persistVacancy("function5", "full-time", "Public", "Germany", 0);

        List<Vacancy> results = vacancyRepository.findAll(where(withIndustry("IT")));

        assertEquals(2, results.size());

        var firstResult = results.get(0);
        assertEquals("IT", firstResult.getIndustry());
    }

    @Test
    void testWithCountry() {
        persistVacancy("function1", "full-time", "IT", "Belgium", 1);
        persistVacancy("function2", "part-time", "IT", "Belgium", 3);
        persistVacancy("function3", "half-time", "Finance", "Italy", 0);
        persistVacancy("function4", "full-time", "Finance", "Belgium", 7);
        persistVacancy("function5", "full-time", "Public", "Germany", 0);

        List<Vacancy> results = vacancyRepository.findAll(where(withCountry("italy")));

        assertEquals(1, results.size());

        var firstResult = results.get(0);
        assertEquals("Italy", firstResult.getCountry());
    }

    @Test
    void testWithContractType() {
        persistVacancy("function1", "full-time", "IT", "Belgium", 1);
        persistVacancy("function2", "part-time", "IT", "Belgium", 3);
        persistVacancy("function3", "half-time", "Finance", "Italy", 0);
        persistVacancy("function4", "Full-Time", "Finance", "Belgium", 7);
        persistVacancy("function5", "full-time", "Public", "Germany", 0);

        List<Vacancy> results = vacancyRepository.findAll(where(withContractType("full-time")));

        assertEquals(3, results.size());

        var firstResult = results.get(0);
        assertEquals("full-time", firstResult.getContractType());
    }

    @Test
    void testWithFunctionTitle() {
        persistVacancy("function1", "full-time", "IT", "Belgium", 1);
        persistVacancy("junior java consultant", "part-time", "IT", "Belgium", 3);
        persistVacancy("function3", "half-time", "Finance", "Italy", 0);
        persistVacancy("junior java consultant", "Full-Time", "Finance", "Belgium", 7);
        persistVacancy("function5", "full-time", "Public", "Germany", 0);

        List<Vacancy> results = vacancyRepository.findAll(where(withFunctionTitle("junior java consultant")));

        assertEquals(2, results.size());

        var firstResult = results.get(0);
        assertEquals("junior java consultant", firstResult.getFunctionTitle());
    }

}
