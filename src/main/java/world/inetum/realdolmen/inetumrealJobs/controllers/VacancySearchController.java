package world.inetum.realdolmen.inetumrealJobs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.inetum.realdolmen.inetumrealJobs.VacancyFilter;
import world.inetum.realdolmen.inetumrealJobs.jpa.Vacancy;
import world.inetum.realdolmen.inetumrealJobs.services.VacancyService;

import java.util.List;

@RestController
@RequestMapping("/api/vacancies")
public class VacancySearchController {


    private final VacancyService vacancyService;

    @Autowired
    public VacancySearchController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping("/filter/{functionTitle}::{contractType}::{country}::{industry}::{expYears}")
    ResponseEntity<String> findAllVacanciesWithFilter(@PathVariable String functionTitle, @PathVariable String contractType, @PathVariable String country, @PathVariable String industry, @PathVariable String expYears) { //todo refactor

        List<Vacancy> vacancyWithFilter = vacancyService.findVacancyWithFilter(functionTitle, contractType, country, industry, Integer.parseInt(expYears));

        if(vacancyWithFilter.size() ==0){
            return new ResponseEntity<>(
                    "No vacancies Found", HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(
                    vacancyWithFilter.size()+" vacancies found !", HttpStatus.OK);
        }


    }
}
