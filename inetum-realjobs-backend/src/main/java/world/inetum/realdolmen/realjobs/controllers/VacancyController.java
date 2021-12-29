package world.inetum.realdolmen.realjobs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import world.inetum.realdolmen.realjobs.entities.Vacancy;
import world.inetum.realdolmen.realjobs.entities.enums.ContractType;
import world.inetum.realdolmen.realjobs.exceptions.EndpointException;
import world.inetum.realdolmen.realjobs.exceptions.messages.GlobalExceptionMessage;
import world.inetum.realdolmen.realjobs.payload.dtos.ApplicationReadDto;
import world.inetum.realdolmen.realjobs.payload.dtos.VacancyReadDto;
import world.inetum.realdolmen.realjobs.payload.mappers.ApplicationMapper;
import world.inetum.realdolmen.realjobs.payload.mappers.VacancyMapper;
import world.inetum.realdolmen.realjobs.services.ApplicationService;
import world.inetum.realdolmen.realjobs.services.VacancyService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/vacancies")
public class VacancyController extends BaseController {

    private final VacancyService vacancyService;
    private final VacancyMapper vacancyMapper;
    private final ApplicationService applicationService;
    private final ApplicationMapper applicationMapper;

    @Autowired
    public VacancyController(VacancyService vacancyService, VacancyMapper vacancyMapper, ApplicationService applicationService, ApplicationMapper applicationMapper) {
        this.vacancyService = vacancyService;
        this.vacancyMapper = vacancyMapper;
        this.applicationService = applicationService;
        this.applicationMapper = applicationMapper;
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
    public List<VacancyReadDto> findAllVacancies() {
        return vacancyService.findAll()
                .stream()
                .map((vacancy) -> {
                    // TODO - Implement proper mapping.
                    VacancyReadDto dto = new VacancyReadDto();
                    dto.setFunctionTitle(vacancy.getFunctionTitle());

                    return dto;
                })
                .toList();
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

    @GetMapping("{id}/applications")
    @RolesAllowed("RECRUITER")
    public List<ApplicationReadDto> getApplications(@PathVariable("id") long id) {
        Vacancy vacancy = vacancyService.findVacancyById(id)
                .orElseThrow(() -> new EndpointException(HttpStatus.NOT_FOUND, GlobalExceptionMessage.NOT_FOUND));

        if (!vacancyService.checkVacancyAccess(vacancy, getCurrentEmail())) {
            throw new AccessDeniedException("You can't access this vacancy's applications.");
        }

        return applicationService.findAllByVacancyId(id)
                .stream()
                .map(applicationMapper::toDto)
                .toList();
    }

}
