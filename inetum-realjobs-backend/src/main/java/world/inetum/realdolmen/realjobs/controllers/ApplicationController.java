package world.inetum.realdolmen.realjobs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import world.inetum.realdolmen.realjobs.payload.dtos.ApplicationReadDto;
import world.inetum.realdolmen.realjobs.payload.dtos.ApplicationUpdateStatusDto;
import world.inetum.realdolmen.realjobs.payload.mappers.ApplicationMapper;
import world.inetum.realdolmen.realjobs.services.ApplicationService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ApplicationMapper applicationMapper;

    @Autowired
    public ApplicationController(ApplicationService applicationService, ApplicationMapper applicationMapper) {
        this.applicationService = applicationService;
        this.applicationMapper = applicationMapper;
    }
    
    @PutMapping("{id}/status")
    @RolesAllowed("RECRUITER")
    public ApplicationReadDto updateApplicationStatus(@PathVariable("id") long applicationId,
                                                      @Valid @RequestBody ApplicationUpdateStatusDto dto) {
        return applicationMapper.toDto(applicationService.updateApplicationStatus(applicationId, dto));
    }
}
