package world.inetum.realdolmen.realjobs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import world.inetum.realdolmen.realjobs.entities.Application;
import world.inetum.realdolmen.realjobs.entities.Vacancy;
import world.inetum.realdolmen.realjobs.exceptions.EndpointException;
import world.inetum.realdolmen.realjobs.exceptions.messages.GlobalExceptionMessage;
import world.inetum.realdolmen.realjobs.payload.dtos.ApplicationReadDto;
import world.inetum.realdolmen.realjobs.payload.dtos.ApplicationUpdateStatusDto;
import world.inetum.realdolmen.realjobs.payload.mappers.ApplicationMapper;
import world.inetum.realdolmen.realjobs.services.ApplicationService;
import world.inetum.realdolmen.realjobs.services.VacancyService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/applications")
public class ApplicationController extends BaseController {

    private final VacancyService vacancyService;
    private final ApplicationService applicationService;
    private final ApplicationMapper applicationMapper;

    @Autowired
    public ApplicationController(VacancyService vacancyService, ApplicationService applicationService, ApplicationMapper applicationMapper) {
        this.vacancyService = vacancyService;
        this.applicationService = applicationService;
        this.applicationMapper = applicationMapper;
    }

    @PutMapping("{id}/status")
    @RolesAllowed("RECRUITER")
    public ApplicationReadDto updateApplicationStatus(
            @PathVariable("id") long id,
            @Valid @RequestBody ApplicationUpdateStatusDto dto
    ) {
        if (dto.getId() != id) {
            throw new EndpointException(GlobalExceptionMessage.ID_MISMATCH);
        }

        Application application = applicationService.findById(id)
                .orElseThrow(() -> new EndpointException(HttpStatus.NOT_FOUND, GlobalExceptionMessage.NOT_FOUND));

        Vacancy vacancy = application.getVacancy();
        if (!vacancyService.checkVacancyAccess(vacancy, getCurrentEmail())) {
            throw new AccessDeniedException("You can't access this vacancy's applications.");
        }

        var response = applicationService.updateApplicationStatus(application, dto);

        return applicationMapper.toDto(response);
    }

}
