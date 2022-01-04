package world.inetum.realdolmen.realjobs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import world.inetum.realdolmen.realjobs.entities.enums.ResumeStatus;
import world.inetum.realdolmen.realjobs.payload.dtos.*;
import world.inetum.realdolmen.realjobs.payload.mappers.AccountMapper;
import world.inetum.realdolmen.realjobs.payload.mappers.ResumeMapper;
import world.inetum.realdolmen.realjobs.security.SecurityService;
import world.inetum.realdolmen.realjobs.services.ResumeService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    private final ResumeService resumeService;
    private final ResumeMapper resumeMapper;
    private final AccountMapper accountMapper;
    private final SecurityService securityService;

    @Autowired
    public ResumeController(ResumeService resumeService, ResumeMapper resumeMapper, AccountMapper accountMapper, SecurityService securityService) {
        this.resumeService = resumeService;
        this.resumeMapper = resumeMapper;
        this.accountMapper = accountMapper;
        this.securityService = securityService;
    }

    @GetMapping
    @RolesAllowed("JOBSEEKER")
    public ResumeReadDto getResume() {
        return resumeMapper.toDto(resumeService.getResume(), securityService.getCurrentUser());
    }

    @PostMapping("/skill")
    @RolesAllowed("JOBSEEKER")
    public List<SkillReadDto> addSkill(@Valid @RequestBody SkillCreateDto newSkill) {
        return resumeService.addSkill(resumeMapper.toEntity(newSkill)).stream().map(resumeMapper::toDto).toList();
    }

    @DeleteMapping("/skill/{id}")
    @RolesAllowed("JOBSEEKER")
    public List<SkillReadDto> removeSkill(@Valid @PathVariable("id") long id) {
        return resumeService.removeSkill(id).stream().map(resumeMapper::toDto).toList();
    }

    @GetMapping("/skill")
    @RolesAllowed("JOBSEEKER")
    public List<SkillReadDto> getSkills() {
        return resumeService.getSkills().stream().map(resumeMapper::toDto).toList();
    }

    @PostMapping("/language")
    @RolesAllowed("JOBSEEKER")
    public List<LanguageReadDto> addLanguage(@Valid @RequestBody LanguageCreateDto newLanguage) {
        return resumeService.addLanguage(resumeMapper.toEntity(newLanguage)).stream().map(resumeMapper::toDto).toList();
    }

    @DeleteMapping("/language/{id}")
    @RolesAllowed("JOBSEEKER")
    public List<LanguageReadDto> removeLanguage(@Valid @PathVariable("id") long id) {
        return resumeService.removeLanguage(id).stream().map(resumeMapper::toDto).toList();
    }

    @GetMapping("/language")
    @RolesAllowed("JOBSEEKER")
    public List<LanguageReadDto> getLanguages() {
        return resumeService.getLanguages().stream().map(resumeMapper::toDto).toList();
    }

    @PostMapping("/education")
    @RolesAllowed("JOBSEEKER")
    public List<EducationReadDto> addEducation(@Valid @RequestBody EducationCreateDto newEducation) {
        return resumeService.addEducation(resumeMapper.toEntity(newEducation)).stream().map(resumeMapper::toDto).toList();
    }

    @DeleteMapping("/education/{id}")
    @RolesAllowed("JOBSEEKER")
    public List<EducationReadDto> removeEducation(@Valid @PathVariable("id") long id) {
        return resumeService.removeEducation(id).stream().map(resumeMapper::toDto).toList();
    }

    @GetMapping("/education")
    @RolesAllowed("JOBSEEKER")
    public List<EducationReadDto> getEducationList() {
        return resumeService.getEducationList().stream().map(resumeMapper::toDto).toList();
    }

    @PostMapping("/experience")
    @RolesAllowed("JOBSEEKER")
    public List<ExperienceReadDto> addExperience(@Valid @RequestBody ExperienceCreateDto newExperience) {
        return resumeService.addExperience(resumeMapper.toEntity(newExperience)).stream().map(resumeMapper::toDto).toList();
    }

    @DeleteMapping("/experience/{id}")
    @RolesAllowed("JOBSEEKER")
    public List<ExperienceReadDto> removeExperience(@Valid @PathVariable("id") long id) {
        return resumeService.removeExperience(id).stream().map(resumeMapper::toDto).toList();
    }

    @GetMapping("/experience")
    @RolesAllowed("JOBSEEKER")
    public List<ExperienceReadDto> getExperienceList() {
        return resumeService.getExperienceList().stream().map(resumeMapper::toDto).toList();
    }

    @PostMapping("/summary")
    @RolesAllowed("JOBSEEKER")
    public SingleValueDto<String> setSummary(@RequestBody @NotBlank SingleValueDto<String> summary) {
        return new SingleValueDto<>(resumeService.setSummary(summary.getValue()));
    }

    @GetMapping("/summary")
    @RolesAllowed("JOBSEEKER")
    public SingleValueDto<String> getSummary() {
        return new SingleValueDto<>(resumeService.getSummary());
    }

    @PostMapping("/status")
    @RolesAllowed("JOBSEEKER")
    public SingleValueDto<ResumeStatus> setStatus(@RequestBody @NotNull SingleValueDto<ResumeStatus> status) {
        return new SingleValueDto<>(resumeService.setStatus(status.getValue()));
    }

    @GetMapping("/status")
    @RolesAllowed("JOBSEEKER")
    public SingleValueDto<ResumeStatus> getStatus() {
        return new SingleValueDto<>(resumeService.getStatus());
    }

    @GetMapping("/account")
    @RolesAllowed("JOBSEEKER")
    public AccountResumeReadDto getAccount() {
        return accountMapper.toDto(resumeService.getAccount());
    }
}
