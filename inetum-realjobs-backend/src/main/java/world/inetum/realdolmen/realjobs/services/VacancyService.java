package world.inetum.realdolmen.realjobs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.realjobs.entities.Account;
import world.inetum.realdolmen.realjobs.entities.Recruiter;
import world.inetum.realdolmen.realjobs.entities.Vacancy;
import world.inetum.realdolmen.realjobs.entities.enums.ContractType;
import world.inetum.realdolmen.realjobs.repositories.VacancyRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;
import static world.inetum.realdolmen.realjobs.repositories.VacancySpecification.*;

@Service
public class VacancyService {

    private final AccountService accountService;
    private final VacancyRepository vacancyRepository;

    @Autowired
    public VacancyService(AccountService accountService, VacancyRepository vacancyRepository) {
        this.accountService = accountService;
        this.vacancyRepository = vacancyRepository;
    }

    public Vacancy addVacancy(Vacancy vacancy) {
        return vacancyRepository.save(vacancy);
    }

    public Optional<Vacancy> findVacancyById(Long id) {
        return vacancyRepository.findById(id);
    }

    public List<Vacancy> findAll() {
        return vacancyRepository.findAll();
    }

    public List<Vacancy> findVacancyWithFilter(String functionTitle, ContractType contractType, Long country_id, String industry, Integer requiredYearsOfExperience) {
        return vacancyRepository.findAll(where(withContractType(contractType))
                .and(withCountry(country_id))
                .and(withRequiredYearsOfExperience(requiredYearsOfExperience))
                .and(withIndustry(industry))
                .and(withFunctionTitle(functionTitle))
        );
    }

    public boolean checkVacancyAccess(Vacancy vacancy, final String email) {
        boolean hasAccess = false;

        if (vacancy.getRecruiter() != null) {
            hasAccess = vacancy.getRecruiter().getEmail().equals(email);
        } else if (vacancy.getCompany() != null) {
            Optional<Account> accountOptional = accountService.findByEmail(email);

            if (accountOptional.isPresent()
                    && accountOptional.get() instanceof Recruiter recruiter
                    && recruiter.getCompany() != null) {
                hasAccess = vacancy.getCompany().getId().equals(recruiter.getCompany().getId());
            }
        }

        return hasAccess;
    }

}
