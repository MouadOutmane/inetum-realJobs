package world.inetum.realdolmen.inetumrealJobs.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import world.inetum.realdolmen.inetumrealJobs.BaseRepositoryTest;
import world.inetum.realdolmen.inetumrealJobs.entities.Address;
import world.inetum.realdolmen.inetumrealJobs.entities.Company;
import world.inetum.realdolmen.inetumrealJobs.entities.Country;
import world.inetum.realdolmen.inetumrealJobs.entities.Vacancy;
import world.inetum.realdolmen.inetumrealJobs.entities.enums.ContractType;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class VacancyServiceTest extends BaseRepositoryTest {

    private VacancyService vacancyService;

    @BeforeEach
    void setUp() {
        vacancyService = new VacancyService(vacancyRepository);
    }

    @Test
    public void givenVacancyRepository_whenSaveAndRetrieveEntity_Success() {
        Country country = new Country();
        country.setName("Brazil");

        Company company = new Company();
        company.setName("QSurfers");
        company.setCity("Rio de Janeiro");
        company.setCountry(country);
        company.setIndustry("Sports");
        company.setLogo(null);

        Address address = new Address();
        address.setStreetName("street");
        address.setHouseNumber("36");
        address.setBox(null);
        address.setCity("Rio de Janeiro");
        address.setPostalCode("10125");
        address.setCountry(country);

        Vacancy vacancy = new Vacancy();
        vacancy.setFunctionTitle("Surfer");
        vacancy.setContractType(ContractType.FULL_TIME);
        vacancy.setFunctionDescription("surf waves");
        vacancy.setCompany(company);
        vacancy.setAddress(address);
        vacancy.setRequiredYearsOfExperience(3);
        vacancy.setRequirements(null);
        vacancy.setOffer("4k/month");
        vacancy.setRecruiter(null);

        Vacancy addedVacancy = vacancyService.addVacancy(vacancy);

        Optional<Vacancy> foundOptional = vacancyService.findVacancyById(addedVacancy.getId());
        assertTrue(foundOptional.isPresent());

        Vacancy found = foundOptional.get();
        assertEquals(addedVacancy.getAddress().getCity(), found.getAddress().getCity());
        assertEquals(addedVacancy.getCompany().getIndustry(), found.getCompany().getIndustry());
        assertEquals(addedVacancy.getContractType(), found.getContractType());
        assertEquals(addedVacancy.getCompany().getName(), found.getCompany().getName());
    }

    @Test
    public void findVacancyWithFilter_Success() {
        Country country1 = persistCountry("Italy");
        Country country2 = persistCountry("Belgium");

        Company company1 = persistCompany("company1", "IT", country1);
        Company company2 = persistCompany("company1", "Finance", country1);

        persistVacancy("junior java consultant", ContractType.FULL_TIME, createAddress(country1), null, 0);
        persistVacancy("senior java consultant", ContractType.FLEXI, createAddress(country1), null, 0);
        persistVacancy("senior java consultant", ContractType.FULL_TIME, createAddress(country1), company1, 3);
        persistVacancy("senior java consultant", ContractType.FULL_TIME, createAddress(country1), company2, 3);
        persistVacancy("senior java consultant", ContractType.FULL_TIME, createAddress(country2), company1, 3);

        List<Vacancy> vacancies = vacancyService.findVacancyWithFilter("senior java consultant", ContractType.FULL_TIME, country1.getId(), "IT", 3);

        assertEquals(1, vacancies.size(), "Expected a single result.");

        Vacancy vacancy = vacancies.get(0);
        assertEquals(ContractType.FULL_TIME, vacancy.getContractType());
        assertEquals("senior java consultant", vacancy.getFunctionTitle());
        assertEquals("Italy", vacancy.getAddress().getCountry().getName());
        assertEquals("IT", vacancy.getCompany().getIndustry());
        assertTrue(3 >= vacancy.getRequiredYearsOfExperience());
    }

}
