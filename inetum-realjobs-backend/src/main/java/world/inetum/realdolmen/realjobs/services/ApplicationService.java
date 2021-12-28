package world.inetum.realdolmen.realjobs.services;

import org.springframework.stereotype.Service;
import world.inetum.realdolmen.realjobs.entities.Application;
import world.inetum.realdolmen.realjobs.repositories.ApplicationRepository;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public List<Application> findAllByVacancyId(long vacancyId) {
        return applicationRepository.findAllByVacancyId(vacancyId);
    }

}
