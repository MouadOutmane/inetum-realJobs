package world.inetum.realdolmen.inetumrealJobs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import world.inetum.realdolmen.inetumrealJobs.dtos.VacancyDto;
import world.inetum.realdolmen.inetumrealJobs.entities.Vacancy;
import world.inetum.realdolmen.inetumrealJobs.services.VacancyService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
    ResponseEntity<List<Vacancy>> findAllVacanciesWithFilter(@RequestParam String functionTitle, @RequestParam String contractType, @RequestParam Long country_id, @RequestParam String industry, @RequestParam String requiredYearsOfExperience) {
        List<Vacancy> results = vacancyService.findVacancyWithFilter(functionTitle, contractType, country_id, industry, Integer.parseInt(requiredYearsOfExperience));
        if (results.isEmpty()) {
            return new ResponseEntity<>(results, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/all")
    List<VacancyDto> findAllVacancies() {
        List<Vacancy> vacancies = vacancyService.findAll();


        List<VacancyDto> vacanciesDto = new ArrayList<>();

        for (Vacancy vacancy: vacancies) {
            VacancyDto vacDto = new VacancyDto();
            vacDto.setId(vacancy.getId());
            vacDto.setAddress(vacancy.getAddress().toString());
            vacDto.setContractType(vacancy.getContractType());
            vacDto.setCompanyName(vacancy.getCompany().getName());
            vacDto.setFunctionTitle(vacancy.getFunctionTitle());
            vacDto.setOffer(vacancy.getOffer());
            vacDto.setRequirements(vacancy.getRequirements());
            vacDto.setFunctionDescription(vacancy.getFunctionDescription());
            vacDto.setRequiredYearsOfExperience(vacancy.getRequiredYearsOfExperience());

            vacanciesDto.add(vacDto);
        }

        return vacanciesDto;
    }

    @PostMapping("/create")
    Vacancy newVacancy(@Valid @RequestBody Vacancy newVacancy) {
        return vacancyService.addVacancy(newVacancy);
    }
}
