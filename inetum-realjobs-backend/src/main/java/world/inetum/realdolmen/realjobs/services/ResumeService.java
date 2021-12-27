package world.inetum.realdolmen.realjobs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.realjobs.entities.*;
import world.inetum.realdolmen.realjobs.entities.enums.ResumeStatus;
import world.inetum.realdolmen.realjobs.repositories.AccountRepository;
import world.inetum.realdolmen.realjobs.security.SecurityService;

import java.util.List;

@Service
public class ResumeService {

    private final SecurityService securityService;
    private final AccountRepository accountRepository;

    @Autowired
    public ResumeService(SecurityService securityService, AccountRepository accountRepository) {
        this.securityService = securityService;
        this.accountRepository = accountRepository;
    }

    public List<Skill> addSkill(Skill newSkill) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        resume.addSkill(newSkill);
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        return resume.getSkills();
    }

    public List<Skill> removeSkill(long id) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        resume.getSkills().removeIf(i -> i.getId().equals(id));
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        return resume.getSkills();
    }

    public List<Skill> getSkills() {
        return getResume().getSkills();
    }

    public List<Language> addLanguage(Language newLanguage) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        resume.addLanguage(newLanguage);
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        return resume.getLanguages();
    }

    public List<Language> removeLanguage(long id) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        resume.getLanguages().removeIf(i -> i.getId().equals(id));
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        return resume.getLanguages();
    }

    public List<Language> getLanguages() {
        return getResume().getLanguages();
    }

    public List<Education> addEducation(Education newEducation) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        resume.addEducation(newEducation);
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        return resume.getEducationList();
    }

    public List<Education> removeEducation(long id) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        resume.getEducationList().removeIf(i -> i.getId().equals(id));
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        return resume.getEducationList();
    }

    public List<Education> getEducationList() {
        return getResume().getEducationList();
    }

    public List<Experience> addExperience(Experience newExperience) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        resume.addExperience(newExperience);
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        return resume.getExperienceList();
    }

    public List<Experience> removeExperience(long id) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        resume.getExperienceList().removeIf(i -> i.getId().equals(id));
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        return resume.getExperienceList();
    }

    public List<Experience> getExperienceList() {
        return getResume().getExperienceList();
    }

    public String setSummary(String newSummary) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        resume.setSummary(newSummary);
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        return resume.getSummary();
    }

    public String getSummary() {
        return getResume().getSummary();
    }

    public ResumeStatus setStatus(ResumeStatus newStatus) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        resume.setStatus(newStatus);
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        return resume.getStatus();
    }

    public ResumeStatus getStatus() {
        return getResume().getStatus();
    }

    private Resume getResume() {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        return currentUserJs.getResume();
    }
}
