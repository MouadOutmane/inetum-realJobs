package world.inetum.realdolmen.inetumrealJobs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.inetumrealJobs.jpa.Vacancy;
import world.inetum.realdolmen.inetumrealJobs.repositories.VacancyRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.jpa.domain.Specification.where;
import static world.inetum.realdolmen.inetumrealJobs.repositories.VacancySpecification.*;
import static world.inetum.realdolmen.inetumrealJobs.repositories.VacancySpecification.withRequiredYearsOfExperience;

@Service
public class VacancyService {

    private final VacancyRepository vacancyRepository;

    @Autowired
    public VacancyService(VacancyRepository vacancyRepository){
        this.vacancyRepository = vacancyRepository;
    }


    public Vacancy addVacancy(Vacancy vacancy){
        return vacancyRepository.save(vacancy);
    }

    public Optional<Vacancy> findVacancyById(Long id){
        return vacancyRepository.findById(id);
    }

    public List<Vacancy> findAll(){
        return vacancyRepository.findAll();
    }

    public List<Vacancy> findVacancyWithFilter(String functionTitle, String contractType, String country, String industry, Integer requiredYearsOfExperience){

        List<Vacancy> results = vacancyRepository.findAll(where(withContractType(contractType))
                .and(withCountry(country))
                .and(withRequiredYearsOfExperience(requiredYearsOfExperience))
                .and(withIndustry(industry))
                .and(withFunctionTitle(functionTitle))
        );

        return results;
    }

}
