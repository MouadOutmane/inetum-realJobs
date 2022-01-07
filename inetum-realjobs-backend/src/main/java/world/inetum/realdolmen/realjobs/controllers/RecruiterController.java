package world.inetum.realdolmen.realjobs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.inetum.realdolmen.realjobs.entities.Vacancy;
import world.inetum.realdolmen.realjobs.payload.dtos.RecruiterOverviewDto;
import world.inetum.realdolmen.realjobs.services.RecruiterService;
import world.inetum.realdolmen.realjobs.services.VacancyService;

import javax.annotation.security.RolesAllowed;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/recruiters")
public class RecruiterController {
    private final VacancyService vacancyService;
    private final RecruiterService recruiterService;
    private Integer amountOfApplicants;
    private String fullRecruiterName;

    @Autowired
    public RecruiterController(VacancyService vacancyService, RecruiterService recruiterService) {
        this.vacancyService = vacancyService;
        this.recruiterService = recruiterService;
    }

    // TODO: add test for controller
    @GetMapping
    @RolesAllowed("RECRUITER")
    public ResponseEntity<List<RecruiterOverviewDto>> findAllVacancies() {
        List<Vacancy> allVacancies = recruiterService.findAll();
        if (allVacancies == null || allVacancies.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
        List<RecruiterOverviewDto> dtos = allVacancies
                .stream()
                .map(
                        (vacancy) -> {
                            RecruiterOverviewDto dto = new RecruiterOverviewDto();
                            dto.setVacancyId(vacancy.getId());
                            dto.setFunctionTitle(vacancy.getFunctionTitle());
                            dto.setPostedOn(vacancy.getCreatedOn());
                            dto.setRecruiterId(vacancy.getRecruiter().getId());
                            fullRecruiterName = recruiterService.getFullRecruiterName(vacancy);
                            dto.setRecruiterFullName(fullRecruiterName);
                            amountOfApplicants = recruiterService.getAmountOfApplicants(vacancy.getId());
                            dto.setAmountOfApplicants(amountOfApplicants);
                            return dto;
                        }
                ).toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
