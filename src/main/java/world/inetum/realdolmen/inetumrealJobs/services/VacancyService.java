package world.inetum.realdolmen.inetumrealJobs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.inetumrealJobs.jpa.Vacancy;
import world.inetum.realdolmen.inetumrealJobs.repositories.VacancyRepository;

import java.util.Optional;

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

    public Optional<Vacancy> findVacancy(Long id){
        return vacancyRepository.findById(id);
    }
}
