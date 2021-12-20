package world.inetum.realdolmen.inetumrealJobs.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import world.inetum.realdolmen.inetumrealJobs.BaseRepositoryTest;
import world.inetum.realdolmen.inetumrealJobs.entities.Company;
import world.inetum.realdolmen.inetumrealJobs.entities.Country;
import world.inetum.realdolmen.inetumrealJobs.entities.Vacancy;
import world.inetum.realdolmen.inetumrealJobs.entities.enums.ContractType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.data.jpa.domain.Specification.where;
import static world.inetum.realdolmen.inetumrealJobs.repositories.VacancySpecification.*;

@SuppressWarnings("ConstantConditions")
@DataJpaTest
public class VacancySpecificationTest extends BaseRepositoryTest {

    @Test
    void findAll_FullTimeItaly3YearsOfExperienceVacancies_True() {
        Country country1 = persistCountry("Belgium");
        Country country2 = persistCountry("France");
        Country country3 = persistCountry("Germany");

        Company company1 = persistCompany("name1", "industry1", country1);
        Company company2 = persistCompany("name2", "industry2", country2);
        Company company3 = persistCompany("name3", "industry3", country3);

        persistVacancy("function1", ContractType.PART_TIME, createAddress(country1), company1, 1);
        persistVacancy("function2", ContractType.PART_TIME, createAddress(country1), company1, 3);
        persistVacancy("function3", ContractType.FLEXI, createAddress(country2), company2, 0);
        persistVacancy("function4", ContractType.FULL_TIME, createAddress(country1), company1, 0);
        persistVacancy("function5", ContractType.FULL_TIME, createAddress(country2), company2, 4);
        persistVacancy("function6", ContractType.FULL_TIME, createAddress(country1), company1, 8);
        persistVacancy("function7", ContractType.FULL_TIME, createAddress(country2), company2, 7);
        persistVacancy("function8", ContractType.FULL_TIME, createAddress(country3), company3, 0);


        Integer requiredYearsOfExperience = 3;
        String industry = null;

        List<Vacancy> results = vacancyRepository.findAll(
                where(withContractType(ContractType.FULL_TIME))
                        .and(withCountry(country1.getId()))
                        .and(withRequiredYearsOfExperience(requiredYearsOfExperience))
                        .and(withIndustry(industry))
        );

        assertEquals(1, results.size());

        var firstResult = results.get(0);
        assertEquals("Belgium", firstResult.getAddress().getCountry().getName());
    }

    @Test
    void testWithNullIndustry() {
        Country country1 = persistCountry("Belgium");
        Country country2 = persistCountry("France");
        Country country3 = persistCountry("Germany");

        Company company1 = persistCompany("name1", "industry1", country1);
        Company company2 = persistCompany("name2", "industry2", country2);
        Company company3 = persistCompany("name3", "industry3", country3);

        persistVacancy("function1", ContractType.PART_TIME, createAddress(country1), company1, 1);
        persistVacancy("function2", ContractType.PART_TIME, createAddress(country1), company1, 3);
        persistVacancy("function3", ContractType.FLEXI, createAddress(country2), company2, 0);
        persistVacancy("function4", ContractType.FULL_TIME, createAddress(country1), company1, 0);
        persistVacancy("function5", ContractType.FULL_TIME, createAddress(country2), company2, 4);
        persistVacancy("function6", ContractType.FULL_TIME, createAddress(country1), company1, 8);
        persistVacancy("function7", ContractType.FULL_TIME, createAddress(country2), company2, 7);
        persistVacancy("function8", ContractType.FULL_TIME, createAddress(country3), company3, 0);

        List<Vacancy> results = vacancyRepository.findAll(where(withIndustry(null)));

        assertEquals(8, results.size());
    }

    @Test
    void testWithNullCountry() {
        Country country1 = persistCountry("Belgium");
        Country country2 = persistCountry("France");
        Country country3 = persistCountry("Germany");

        Company company1 = persistCompany("name1", "industry1", country1);
        Company company2 = persistCompany("name2", "industry2", country2);
        Company company3 = persistCompany("name3", "industry3", country3);

        persistVacancy("function1", ContractType.PART_TIME, createAddress(country1), company1, 1);
        persistVacancy("function2", ContractType.PART_TIME, createAddress(country1), company1, 3);
        persistVacancy("function3", ContractType.FLEXI, createAddress(country2), company2, 0);
        persistVacancy("function4", ContractType.FULL_TIME, createAddress(country1), company1, 0);
        persistVacancy("function5", ContractType.FULL_TIME, createAddress(country2), company2, 4);
        persistVacancy("function6", ContractType.FULL_TIME, createAddress(country1), company1, 8);
        persistVacancy("function7", ContractType.FULL_TIME, createAddress(country2), company2, 7);
        persistVacancy("function8", ContractType.FULL_TIME, createAddress(country3), company3, 0);

        List<Vacancy> results = vacancyRepository.findAll(where(withCountry(null)));

        assertEquals(8, results.size());
    }

    @Test
    void testWithNullFunctionTitle() {
        Country country1 = persistCountry("Belgium");
        Country country2 = persistCountry("France");
        Country country3 = persistCountry("Germany");

        Company company1 = persistCompany("name1", "industry1", country1);
        Company company2 = persistCompany("name2", "industry2", country2);
        Company company3 = persistCompany("name3", "industry3", country3);

        persistVacancy("function1", ContractType.PART_TIME, createAddress(country1), company1, 1);
        persistVacancy("function2", ContractType.PART_TIME, createAddress(country1), company1, 3);
        persistVacancy("function3", ContractType.FLEXI, createAddress(country2), company2, 0);
        persistVacancy("function4", ContractType.FULL_TIME, createAddress(country1), company1, 0);
        persistVacancy("function5", ContractType.FULL_TIME, createAddress(country2), company2, 4);
        persistVacancy("function6", ContractType.FULL_TIME, createAddress(country1), company1, 8);
        persistVacancy("function7", ContractType.FULL_TIME, createAddress(country2), company2, 7);
        persistVacancy("function8", ContractType.FULL_TIME, createAddress(country3), company3, 0);

        List<Vacancy> results = vacancyRepository.findAll(where(withFunctionTitle(null)));

        assertEquals(8, results.size());
    }

    @Test
    void testWithNullContractType() {
        Country country1 = persistCountry("Belgium");
        Country country2 = persistCountry("France");
        Country country3 = persistCountry("Germany");

        Company company1 = persistCompany("name1", "industry1", country1);
        Company company2 = persistCompany("name2", "industry2", country2);
        Company company3 = persistCompany("name3", "industry3", country3);

        persistVacancy("function1", ContractType.PART_TIME, createAddress(country1), company1, 1);
        persistVacancy("function2", ContractType.PART_TIME, createAddress(country1), company1, 3);
        persistVacancy("function3", ContractType.FLEXI, createAddress(country2), company2, 0);
        persistVacancy("function4", ContractType.FULL_TIME, createAddress(country1), company1, 0);
        persistVacancy("function5", ContractType.FULL_TIME, createAddress(country2), company2, 4);
        persistVacancy("function6", ContractType.FULL_TIME, createAddress(country1), company1, 8);
        persistVacancy("function7", ContractType.FULL_TIME, createAddress(country2), company2, 7);
        persistVacancy("function8", ContractType.FULL_TIME, createAddress(country3), company3, 0);

        List<Vacancy> results = vacancyRepository.findAll(where(withContractType(null)));

        assertEquals(8, results.size());
    }

    @Test
    void testWithIndustry() {
        Country country1 = persistCountry("Belgium");
        Country country2 = persistCountry("France");
        Country country3 = persistCountry("Germany");

        Company company1 = persistCompany("name1", "industry1", country1);
        Company company2 = persistCompany("name2", "IT", country2);
        Company company3 = persistCompany("name3", "industry3", country3);

        persistVacancy("function1", ContractType.PART_TIME, createAddress(country1), company1, 1);
        persistVacancy("function2", ContractType.PART_TIME, createAddress(country1), company1, 3);
        persistVacancy("junior java consultant", ContractType.FLEXI, createAddress(country2), company2, 0);
        persistVacancy("function4", ContractType.FULL_TIME, createAddress(country1), company1, 0);
        persistVacancy("function5", ContractType.FULL_TIME, createAddress(country2), company2, 4);
        persistVacancy("junior java consultant", ContractType.FULL_TIME, createAddress(country1), company1, 8);
        persistVacancy("function7", ContractType.FULL_TIME, createAddress(country2), company2, 7);
        persistVacancy("function8", ContractType.FULL_TIME, createAddress(country3), company3, 0);

        List<Vacancy> results = vacancyRepository.findAll(where(withIndustry("IT")));

        assertEquals(3, results.size());

        var firstResult = results.get(0);
        assertEquals("IT", firstResult.getCompany().getIndustry());
    }

    @Test
    void testWithCountry() {
        Country country1 = persistCountry("Belgium");
        Country country2 = persistCountry("France");
        Country country3 = persistCountry("Germany");

        Company company1 = persistCompany("name1", "industry1", country1);
        Company company2 = persistCompany("name2", "industry2", country2);
        Company company3 = persistCompany("name3", "industry3", country3);

        persistVacancy("function1", ContractType.PART_TIME, createAddress(country1), company1, 1);
        persistVacancy("function2", ContractType.PART_TIME, createAddress(country1), company1, 3);
        persistVacancy("junior java consultant", ContractType.FLEXI, createAddress(country2), company2, 0);
        persistVacancy("function4", ContractType.FULL_TIME, createAddress(country1), company1, 0);
        persistVacancy("function5", ContractType.FULL_TIME, createAddress(country2), company2, 4);
        persistVacancy("junior java consultant", ContractType.FULL_TIME, createAddress(country1), company1, 8);
        persistVacancy("function7", ContractType.FULL_TIME, createAddress(country2), company2, 7);
        persistVacancy("function8", ContractType.FULL_TIME, createAddress(country3), company3, 0);

        List<Vacancy> results = vacancyRepository.findAll(where(withCountry(country2.getId())));

        assertEquals(3, results.size());

        var firstResult = results.get(0);
        assertEquals("France", firstResult.getAddress().getCountry().getName());
    }

    @Test
    void testWithContractType() {
        Country country1 = persistCountry("Belgium");
        Country country2 = persistCountry("France");
        Country country3 = persistCountry("Germany");

        Company company1 = persistCompany("name1", "industry1", country1);
        Company company2 = persistCompany("name2", "industry2", country2);
        Company company3 = persistCompany("name3", "industry3", country3);

        persistVacancy("function1", ContractType.PART_TIME, createAddress(country1), company1, 1);
        persistVacancy("function2", ContractType.PART_TIME, createAddress(country1), company1, 3);
        persistVacancy("function3", ContractType.FLEXI, createAddress(country2), company2, 0);
        persistVacancy("function4", ContractType.FULL_TIME, createAddress(country1), company1, 0);
        persistVacancy("function5", ContractType.FULL_TIME, createAddress(country2), company2, 4);
        persistVacancy("function6", ContractType.FULL_TIME, createAddress(country1), company1, 8);
        persistVacancy("function7", ContractType.FULL_TIME, createAddress(country2), company2, 7);
        persistVacancy("function8", ContractType.FULL_TIME, createAddress(country3), company3, 0);

        List<Vacancy> results = vacancyRepository.findAll(where(withContractType(ContractType.FULL_TIME)));

        assertEquals(5, results.size());

        var firstResult = results.get(0);
        assertEquals(ContractType.FULL_TIME, firstResult.getContractType());
    }

    @Test
    void testWithFunctionTitle() {
        Country country1 = persistCountry("Belgium");
        Country country2 = persistCountry("France");
        Country country3 = persistCountry("Germany");

        Company company1 = persistCompany("name1", "industry1", country1);
        Company company2 = persistCompany("name2", "industry2", country2);
        Company company3 = persistCompany("name3", "industry3", country3);

        persistVacancy("function1", ContractType.PART_TIME, createAddress(country1), company1, 1);
        persistVacancy("function2", ContractType.PART_TIME, createAddress(country1), company1, 3);
        persistVacancy("junior java consultant", ContractType.FLEXI, createAddress(country2), company2, 0);
        persistVacancy("function4", ContractType.FULL_TIME, createAddress(country1), company1, 0);
        persistVacancy("function5", ContractType.FULL_TIME, createAddress(country2), company2, 4);
        persistVacancy("junior java consultant", ContractType.FULL_TIME, createAddress(country1), company1, 8);
        persistVacancy("function7", ContractType.FULL_TIME, createAddress(country2), company2, 7);
        persistVacancy("function8", ContractType.FULL_TIME, createAddress(country3), company3, 0);

        List<Vacancy> results = vacancyRepository.findAll(where(withFunctionTitle("junior java consultant")));

        assertEquals(2, results.size());

        var firstResult = results.get(0);
        assertEquals("junior java consultant", firstResult.getFunctionTitle());
    }

}
