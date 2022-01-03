package world.inetum.realdolmen.realjobs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import world.inetum.realdolmen.realjobs.entities.*;
import world.inetum.realdolmen.realjobs.entities.enums.ContractType;
import world.inetum.realdolmen.realjobs.repositories.*;

import java.util.Optional;
import java.util.UUID;

@SuppressWarnings({"SameParameterValue", "unused"})
public abstract class BaseRepositoryTest {

    protected static ObjectMapper mapper;

    @Autowired
    protected AccountRepository accountRepository;
    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CountryRepository countryRepository;
    @Autowired
    protected VacancyRepository vacancyRepository;
    @Autowired
    protected ResetCodeRepository resetCodeRepository;

    @BeforeAll
    protected static void registerMapper() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @BeforeEach
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void cleanDatabase() {
        resetCodeRepository.deleteAll();
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
            return mapper.writeValueAsString(object);
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected ResetCode persistResetCode(Account account, UUID code) {
        ResetCode resetCode = new ResetCode();
        resetCode.setCode(code);
        resetCode.setAccount(account);
        return resetCodeRepository.save(resetCode);
    }
    
}
