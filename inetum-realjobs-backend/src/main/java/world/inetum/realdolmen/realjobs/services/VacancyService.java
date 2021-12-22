package world.inetum.realdolmen.realjobs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.realjobs.entities.Vacancy;
import world.inetum.realdolmen.realjobs.entities.enums.ContractType;
import world.inetum.realdolmen.realjobs.repositories.VacancyRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;
import static world.inetum.realdolmen.realjobs.repositories.VacancySpecification.*;

@Service
public class VacancyService {

    private final VacancyRepository vacancyRepository;

    @Autowired
    public VacancyService(VacancyRepository vacancyRepository) {
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
}