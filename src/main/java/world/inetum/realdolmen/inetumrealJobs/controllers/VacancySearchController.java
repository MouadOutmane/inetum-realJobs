package world.inetum.realdolmen.inetumrealJobs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import world.inetum.realdolmen.inetumrealJobs.entities.Vacancy;
import world.inetum.realdolmen.inetumrealJobs.services.VacancyService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/vacancies")
public class VacancySearchController {


    private final VacancyService vacancyService;

    @Autowired
    public VacancySearchController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping("/filter/")
    ResponseEntity<List<Vacancy>> findAllVacanciesWithFilter(@RequestParam String functionTitle, @RequestParam String contractType, @RequestParam String country, @RequestParam String industry, @RequestParam String requiredYearsOfExperience) {

        List<Vacancy> results = vacancyService.findVacancyWithFilter(functionTitle, contractType, country, industry, Integer.parseInt(requiredYearsOfExperience));

        return new ResponseEntity<>(results, HttpStatus.OK);

    }
}
