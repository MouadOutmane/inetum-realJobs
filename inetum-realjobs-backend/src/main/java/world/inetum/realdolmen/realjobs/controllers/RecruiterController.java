package world.inetum.realdolmen.realjobs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.inetum.realdolmen.realjobs.entities.Application;
import world.inetum.realdolmen.realjobs.entities.Vacancy;
import world.inetum.realdolmen.realjobs.payload.dtos.NotificationDto;
import world.inetum.realdolmen.realjobs.payload.dtos.RecruiterOverviewDto;
import world.inetum.realdolmen.realjobs.services.RecruiterService;
import world.inetum.realdolmen.realjobs.services.VacancyService;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDateTime;
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
    @GetMapping("/all")
    @RolesAllowed("RECRUITER")
    public ResponseEntity<List<RecruiterOverviewDto>> findAllVacancies() {
        List<Vacancy> allVacancies = recruiterService.findAll();
        return getListOfResponseEntity(allVacancies);
    }

    @GetMapping
    @RolesAllowed("RECRUITER")
    public ResponseEntity<List<RecruiterOverviewDto>> findAllVacanciesForCurrentUser() {
        Long recruiterId = recruiterService.getIdOfCurrentUser();
        List<Vacancy> allVacancies = recruiterService.findAllVacanciesByRecruiterId(recruiterId);
        return getListOfResponseEntity(allVacancies);
    }

    @GetMapping("/notifications")
    @RolesAllowed("RECRUITER")
    public ResponseEntity<List<NotificationDto>> getNotificationsForCurrentUser() {
        LocalDateTime timestamp = LocalDateTime.now().minusHours(24L);
//        TODO add current recruiter id as filter
        List<Application> applications = recruiterService.getApplicationsUpdate(12L, timestamp);
        List<NotificationDto> notifications = applications
                .stream()
                .map(
                        (application) -> {
                            NotificationDto dto = new NotificationDto();
                            dto.setNotificationName("New application");
                            dto.setTimestamp(application.getChangedOn());
                            dto.setFunctionTitle(application.getVacancy().getFunctionTitle());
                            dto.setVacancyId(application.getVacancy().getId());
                            dto.setRecruiterId(application.getVacancy().getRecruiter().getId());
                            dto.setAmountOfNewApplicants(1);
                            dto.setIsRead(false);
                            return dto;
                        }
                )
                .toList();

        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/notifications/count")
    @RolesAllowed("RECRUITER")
    public ResponseEntity<Integer> getAmountOfNotifications() {
        LocalDateTime timestamp = LocalDateTime.now().minusHours(24L);
        Integer amount = recruiterService.getApplicationsAfterTimestamp(timestamp);
        return new ResponseEntity<>(amount, HttpStatus.OK);
    }

    private ResponseEntity<List<RecruiterOverviewDto>> getListOfResponseEntity(List<Vacancy> allVacancies) {
        if (allVacancies == null || allVacancies.isEmpty()) {
            RecruiterOverviewDto message = new RecruiterOverviewDto();
            message.setFunctionTitle("Your vacancies will appear here");
            RecruiterOverviewDto advice = new RecruiterOverviewDto();
            advice.setFunctionTitle("Get started and post your first vacancy by clicking 'Post new vacancy");
            List<RecruiterOverviewDto> dtos = List.of(message, advice);
            return new ResponseEntity<>(dtos, HttpStatus.OK);
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
                )
                .toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
