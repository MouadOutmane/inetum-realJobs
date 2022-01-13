package world.inetum.realdolmen.realjobs.services;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.realjobs.entities.Application;
import world.inetum.realdolmen.realjobs.entities.Vacancy;
import world.inetum.realdolmen.realjobs.exceptions.EndpointException;
import world.inetum.realdolmen.realjobs.exceptions.messages.GlobalExceptionMessage;
import world.inetum.realdolmen.realjobs.payload.dtos.ApplicationUpdateStatusDto;
import world.inetum.realdolmen.realjobs.repositories.ApplicationRepository;
import world.inetum.realdolmen.realjobs.security.SecurityService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final VacancyService vacancyService;
    private final SecurityService securityService;

    public ApplicationService(ApplicationRepository applicationRepository, VacancyService vacancyService, SecurityService securityService) {
        this.applicationRepository = applicationRepository;
        this.vacancyService = vacancyService;
        this.securityService = securityService;
    }

    public Optional<Application> findById(long id) {
        return applicationRepository.findById(id);
    }

    public Application updateApplicationStatus(long applicationId, ApplicationUpdateStatusDto dto) {
        Application application = findById(applicationId)
                .orElseThrow(() -> new EndpointException(HttpStatus.NOT_FOUND, GlobalExceptionMessage.NOT_FOUND));

        Vacancy vacancy = application.getVacancy();
        if (!vacancyService.checkVacancyAccess(vacancy, securityService.getCurrentEmail())) {
            throw new AccessDeniedException("You can't access this vacancy's applications.");
        }

        application.setStatus(dto.getStatus());

        return applicationRepository.save(application);
    }

    public Integer getAmountOfApplicants(Long vacancyId) {
        return applicationRepository.countApplicationsByVacancy_Id(vacancyId);
    }

    public List<Application> getByVacancyIdAndTimestamp(Long recruiterId, LocalDateTime timestamp) {
        return applicationRepository.findByVacancyIdAndTimestamp(recruiterId, timestamp);
    }

}
