package world.inetum.realdolmen.realjobs.services;

import org.springframework.stereotype.Service;
import world.inetum.realdolmen.realjobs.entities.Application;
import world.inetum.realdolmen.realjobs.payload.dtos.ApplicationUpdateStatusDto;
import world.inetum.realdolmen.realjobs.repositories.ApplicationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public Optional<Application> findById(long id) {
        return applicationRepository.findById(id);
    }

    public List<Application> findAllByVacancyId(long vacancyId) {
        return applicationRepository.findAllByVacancyId(vacancyId);
    }

    public Application updateApplicationStatus(Application application, ApplicationUpdateStatusDto dto) {
        application.setStatus(dto.getStatus());

        return applicationRepository.save(application);
    }

}
