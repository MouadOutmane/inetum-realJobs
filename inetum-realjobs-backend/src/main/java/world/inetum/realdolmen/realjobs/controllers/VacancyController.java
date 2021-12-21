package world.inetum.realdolmen.realjobs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import world.inetum.realdolmen.realjobs.dtos.VacancyFilterResultDto;
import world.inetum.realdolmen.realjobs.entities.Vacancy;
import world.inetum.realdolmen.realjobs.entities.enums.ContractType;
import world.inetum.realdolmen.realjobs.services.VacancyService;

import javax.validation.Valid;
import java.util.Collections;
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

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<VacancyFilterResultDto>> findAllVacanciesWithFilter(@RequestParam String functionTitle,
                                                                                   @RequestParam(required = false) ContractType contractType,
                                                                                   @RequestParam(name = "country", required = false) Long country_id,
                                                                                   @RequestParam String industry,
                                                                                   @RequestParam(required = false) Integer requiredYearsOfExperience) {
        List<Vacancy> results = vacancyService.findVacancyWithFilter(functionTitle, contractType, country_id, industry, requiredYearsOfExperience);
        if (results.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }

        List<VacancyFilterResultDto> dtos = results
                .stream()
                .map((vacancy) -> {
                    // FIXME - Implement proper mapping.
                    VacancyFilterResultDto dto = new VacancyFilterResultDto();
                    dto.setFunctionTitle(vacancy.getFunctionTitle());

                    return dto;
                })
                .toList();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Vacancy> findAllVacancies() {
        // FIXME - Implement DTOs.
        return vacancyService.findAll();
    }

    @PostMapping("/create")
    public Vacancy newVacancy(@Valid @RequestBody Vacancy newVacancy) {
        // FIXME - Implement DTOs.
        return vacancyService.addVacancy(newVacancy);
    }
}
