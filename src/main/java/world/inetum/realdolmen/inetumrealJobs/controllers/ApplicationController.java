package world.inetum.realdolmen.inetumrealJobs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import world.inetum.realdolmen.inetumrealJobs.DTO.ApplicationDTO;
import world.inetum.realdolmen.inetumrealJobs.entities.Application;
import world.inetum.realdolmen.inetumrealJobs.entities.User;
import world.inetum.realdolmen.inetumrealJobs.entities.Vacancy;
import world.inetum.realdolmen.inetumrealJobs.services.ApplicationService;
import world.inetum.realdolmen.inetumrealJobs.services.UserDetailsServiceImpl;
import world.inetum.realdolmen.inetumrealJobs.services.VacancyService;

import java.time.LocalDateTime;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/application")
public class ApplicationController {


    private final ApplicationService applicationService;

    private final VacancyService vacancyService;

    private final UserDetailsServiceImpl userService;

    @Autowired
    public ApplicationController(ApplicationService applicationService, VacancyService vacancyService, UserDetailsServiceImpl userService) {
        this.applicationService = applicationService;
        this.vacancyService = vacancyService;
        this.userService = userService;
    }

    @PostMapping("/create")
    Application newApplication(@RequestBody ApplicationDTO newApplicationDto) {

        Application newApplication = new Application();
        newApplication.setTimeCreated(LocalDateTime.parse(newApplicationDto.getTimeCreated()));
        newApplication.setStatus(newApplicationDto.getStatus());

        User user = userService.getById(newApplicationDto.getUserId());
        Vacancy vacancy = vacancyService.getById(newApplicationDto.getVacancyId()).orElse(null); // orElseThrow Exception todo

        newApplication.setVacancy(vacancy);
        newApplication.setUser(user);


        return applicationService.addApplication(newApplication);
    }




}
