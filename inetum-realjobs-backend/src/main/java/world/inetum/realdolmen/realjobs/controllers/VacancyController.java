package world.inetum.realdolmen.realjobs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import world.inetum.realdolmen.realjobs.entities.Vacancy;
import world.inetum.realdolmen.realjobs.entities.enums.ContractType;
import world.inetum.realdolmen.realjobs.payload.dtos.VacancyReadDto;
import world.inetum.realdolmen.realjobs.payload.mappers.VacancyMapper;
import world.inetum.realdolmen.realjobs.services.VacancyService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/vacancies")
public class VacancyController {

    private final VacancyService vacancyService;
    private final VacancyMapper vacancyMapper;

    @Autowired
    public VacancyController(VacancyService vacancyService, VacancyMapper vacancyMapper) {
        this.vacancyService = vacancyService;
        this.vacancyMapper = vacancyMapper;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<VacancyReadDto>> findAllVacanciesWithFilter(@RequestParam String functionTitle,
                                                                           @RequestParam(required = false) ContractType contractType,
                                                                           @RequestParam(name = "country", required = false) Long country_id,
                                                                           @RequestParam String industry,
                                                                           @RequestParam(required = false) Integer requiredYearsOfExperience) {
        List<Vacancy> results = vacancyService.findVacancyWithFilter(functionTitle, contractType, country_id, industry, requiredYearsOfExperience);
        if (results.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }

        List<VacancyReadDto> dtos = results
                .stream()
                .map((vacancy) -> {
                    // TODO - Implement proper mapping.
                    VacancyReadDto dto = new VacancyReadDto();
                    dto.setFunctionTitle(vacancy.getFunctionTitle());

                    return dto;
                })
                .toList();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<VacancyReadDto>> findAllVacancies() {
        List<Vacancy> allVacancies = vacancyService.findAll();
        if (allVacancies == null || allVacancies.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
        List<VacancyReadDto> dtos = allVacancies
                .stream()
                .map(
                        (vacancy) -> {
                    VacancyReadDto dto = new VacancyReadDto();
                    dto.setId(vacancy.getId());
                    dto.setFunctionTitle(vacancy.getFunctionTitle());
                    dto.setPostedOn(vacancy.getCreatedOn());
                    dto.setRecruiterId(vacancy.getRecruiter().getId());
//                    TODO - add number of applicants
                    return dto;
                }
                ).toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping("/create")
    public Vacancy newVacancy(@Valid @RequestBody Vacancy newVacancy) {
        // TODO - Implement DTOs.
        return vacancyService.addVacancy(newVacancy);
    }

    @GetMapping("{id}")
    public ResponseEntity<VacancyReadDto> findVacancy(@PathVariable("id") long id) {
        return ResponseEntity.of(
                vacancyService.findVacancyById(id).map(vacancyMapper::toDto)
        );
    }

}
