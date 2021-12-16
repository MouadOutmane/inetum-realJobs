package world.inetum.realdolmen.inetumrealJobs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import world.inetum.realdolmen.inetumrealJobs.entities.Address;
import world.inetum.realdolmen.inetumrealJobs.entities.Company;
import world.inetum.realdolmen.inetumrealJobs.entities.Country;
import world.inetum.realdolmen.inetumrealJobs.entities.Vacancy;
import world.inetum.realdolmen.inetumrealJobs.entities.enums.ContractType;
import world.inetum.realdolmen.inetumrealJobs.repositories.AccountRepository;
import world.inetum.realdolmen.inetumrealJobs.repositories.CompanyRepository;
import world.inetum.realdolmen.inetumrealJobs.repositories.CountryRepository;
import world.inetum.realdolmen.inetumrealJobs.repositories.VacancyRepository;

import java.util.Optional;

@SuppressWarnings({"SameParameterValue"})
public abstract class BaseRepositoryTest {

    @Autowired
    protected AccountRepository accountRepository;
    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CountryRepository countryRepository;
    @Autowired
    protected VacancyRepository vacancyRepository;

    @BeforeEach
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void cleanDatabase() {
        accountRepository.deleteAll();
        vacancyRepository.deleteAll();
        companyRepository.deleteAll();
        countryRepository.deleteAll();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected Vacancy persistVacancy(String function, ContractType contractType, String description, Address address, Company company, int experience, String requirements, String offer) {
        Vacancy vacancy = new Vacancy();
        vacancy.setFunctionTitle(function);
        vacancy.setContractType(contractType);
        vacancy.setFunctionDescription(description);
        vacancy.setCompany(company);
        vacancy.setAddress(address);
        vacancy.setRequiredYearsOfExperience(experience);
        vacancy.setRequirements(requirements);
        vacancy.setOffer(offer);
        vacancy.setRecruiter(null);

        return vacancyRepository.save(vacancy);
    }

    protected Vacancy persistVacancy(String function, ContractType contractType, Address address, Company company, int experience) {
        return persistVacancy(function, contractType, "description", address, company, experience, "requirements", "offer");
    }

    protected Vacancy persistVacancy(String function, ContractType contractType, Company company, int experience) {
        Country country = persistCountry("country");

        Address address = new Address();
        address.setStreetName("street");
        address.setHouseNumber("number");
        address.setBox(null);
        address.setCity("city");
        address.setPostalCode("postal");
        address.setCountry(country);

        return persistVacancy(function, contractType, address, company, experience);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected Country persistCountry(String name) {
        Optional<Country> optionalCountry = countryRepository.findByName(name);
        if (optionalCountry.isPresent()) return optionalCountry.get();

        Country country = new Country();
        country.setName(name);

        return countryRepository.save(country);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected Company persistCompany(String name, String industry, Country country) {
        Company company = new Company();
        company.setName(name);
        company.setCity("city");
        company.setCountry(country);
        company.setIndustry(industry);
        company.setLogo(null);

        return companyRepository.save(company);
    }

    protected static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected Address createAddress(Country country) {
        Address address = new Address();
        address.setStreetName("street");
        address.setHouseNumber("house");
        address.setBox(null);
        address.setCity("city");
        address.setPostalCode("postal");
        address.setCountry(country);

        return address;
    }

}
