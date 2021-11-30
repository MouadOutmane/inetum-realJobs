package world.inetum.realdolmen.inetumrealJobs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import world.inetum.realdolmen.inetumrealJobs.entities.Vacancy;
import world.inetum.realdolmen.inetumrealJobs.services.VacancyService;

import javax.validation.Valid;
import java.util.List;

@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/vacancies")
public class VacancyController {


    private final VacancyService vacancyService;

    @Autowired
    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping("/")
    ResponseEntity<List<Vacancy>> findAllVacanciesWithFilter(@RequestParam String functionTitle, @RequestParam String contractType, @RequestParam String country, @RequestParam String industry, @RequestParam String requiredYearsOfExperience) {

        List<Vacancy> results = vacancyService.findVacancyWithFilter(functionTitle, contractType, country, industry, Integer.parseInt(requiredYearsOfExperience));

        return new ResponseEntity<>(results, HttpStatus.OK);

    }

    @PostMapping("/create")
    Vacancy newEmployee(@Valid @RequestBody Vacancy newVacancy) {
        return vacancyService.addVacancy(newVacancy);
    }
}
