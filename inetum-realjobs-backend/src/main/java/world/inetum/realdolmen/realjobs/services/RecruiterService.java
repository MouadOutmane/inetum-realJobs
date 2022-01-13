package world.inetum.realdolmen.realjobs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.realjobs.entities.Application;
import world.inetum.realdolmen.realjobs.entities.Vacancy;
import world.inetum.realdolmen.realjobs.security.SecurityService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecruiterService {
    private final AccountService accountService;
    private final VacancyService vacancyService;
    private final SecurityService securityService;
    private final ApplicationService applicationService;

    @Autowired
    public RecruiterService(AccountService accountService, VacancyService vacancyService, SecurityService securityService, ApplicationService applicationService) {
        this.accountService = accountService;
        this.vacancyService = vacancyService;
        this.securityService = securityService;
        this.applicationService = applicationService;
    }

    public List<Vacancy> findAll() {
        return vacancyService.findAll();
    }

    public Integer getAmountOfApplicants(Long vacancyId) {
        return applicationService.getAmountOfApplicants(vacancyId);
    }

    public String getFullUserName() {
        String fullUserName = securityService.getCurrentUser().getFirstName() + " "
                + securityService.getCurrentUser().getLastName();
        return fullUserName;
    }

    public String getFullRecruiterName(Vacancy vacancy) {
        return vacancy.getRecruiter().getFirstName() + " " + vacancy.getRecruiter().getLastName();
    }

    public List<Vacancy> findAllVacanciesByRecruiterId(Long recruiterId) {
        return vacancyService.getVacanciesByRecruiterId(recruiterId);
    }

    public Long getIdOfCurrentUser() {
        return securityService.getCurrentUser().getId();
    }

    public List<Application> getApplicationsUpdate(Long vacancyId, LocalDateTime timestamp) {
        return applicationService.getByVacancyIdAndTimestamp(vacancyId, timestamp);
    }
}
