package world.inetum.realdolmen.realjobs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.realjobs.entities.*;
import world.inetum.realdolmen.realjobs.entities.enums.ResumeStatus;
import world.inetum.realdolmen.realjobs.repositories.AccountRepository;
import world.inetum.realdolmen.realjobs.security.SecurityService;

import java.util.List;

@Service
public class ResumeService {

    private final Logger logger = LoggerFactory.getLogger(ResumeService.class);
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
        logger.info("User {} added a skill {}", currentUserJs.getId(), newSkill);
        return resume.getSkills();
    }

    public List<Skill> removeSkill(long id) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        resume.getSkills().removeIf(i -> i.getId().equals(id));
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        logger.info("User {} removed skill {}", currentUserJs.getId(), id);
        return resume.getSkills();
    }

    public List<Skill> getSkills() {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        logger.info("User {} fetched their skills", currentUserJs.getId());
        return currentUserJs.getResume().getSkills();
    }

    public List<Language> addLanguage(Language newLanguage) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        resume.addLanguage(newLanguage);
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        logger.info("User {} added a language {}", currentUserJs.getId(), newLanguage);
        return resume.getLanguages();
    }

    public List<Language> removeLanguage(long id) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        resume.getLanguages().removeIf(i -> i.getId().equals(id));
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        logger.info("User {} removed language {}", currentUserJs.getId(), id);
        return resume.getLanguages();
    }

    public List<Language> getLanguages() {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        logger.info("User {} fetched their languages", currentUserJs.getId());
        return currentUserJs.getResume().getLanguages();
    }

    public List<Education> addEducation(Education newEducation) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        resume.addEducation(newEducation);
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        logger.info("User {} added an education {}", currentUserJs.getId(), newEducation);
        return resume.getEducationList();
    }

    public List<Education> removeEducation(long id) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        resume.getEducationList().removeIf(i -> i.getId().equals(id));
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        logger.info("User {} removed education {}", currentUserJs.getId(), id);
        return resume.getEducationList();
    }

    public List<Education> getEducationList() {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        logger.info("User {} fetched their educationList", currentUserJs.getId());
        return currentUserJs.getResume().getEducationList();
    }

    public List<Experience> addExperience(Experience newExperience) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        resume.addExperience(newExperience);
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        logger.info("User {} added an experience {}", currentUserJs.getId(), newExperience);
        return resume.getExperienceList();
    }

    public List<Experience> removeExperience(long id) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        resume.getExperienceList().removeIf(i -> i.getId().equals(id));
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        logger.info("User {} removed experience {}", currentUserJs.getId(), id);
        return resume.getExperienceList();
    }

    public List<Experience> getExperienceList() {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        logger.info("User {} fetched their experienceList", currentUserJs.getId());
        return currentUserJs.getResume().getExperienceList();
    }

    public String setSummary(String newSummary) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        resume.setSummary(newSummary);
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        logger.info("User {} set their summary", currentUserJs.getId());
        return resume.getSummary();
    }

    public String getSummary() {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        logger.info("User {} fetched their summary", currentUserJs.getId());
        return currentUserJs.getResume().getSummary();
    }

    public ResumeStatus setStatus(ResumeStatus newStatus) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        resume.setStatus(newStatus);
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        logger.info("User {} set their status", currentUserJs.getId());
        return resume.getStatus();
    }

    public ResumeStatus getStatus() {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        logger.info("User {} fetched their status", currentUserJs.getId());
        return currentUserJs.getResume().getStatus();
    }

    public Account getAccount() {
        return securityService.getJobSeeker();
    }
}
