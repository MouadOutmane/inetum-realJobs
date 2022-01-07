package world.inetum.realdolmen.realjobs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.realjobs.entities.Vacancy;
import world.inetum.realdolmen.realjobs.security.SecurityService;

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

}
