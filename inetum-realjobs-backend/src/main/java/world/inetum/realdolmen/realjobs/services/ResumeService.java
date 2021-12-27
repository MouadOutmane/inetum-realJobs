package world.inetum.realdolmen.realjobs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.realjobs.entities.*;
import world.inetum.realdolmen.realjobs.entities.enums.ResumeStatus;
import world.inetum.realdolmen.realjobs.repositories.AccountRepository;
import world.inetum.realdolmen.realjobs.repositories.ResumeRepository;
import world.inetum.realdolmen.realjobs.security.SecurityService;

import java.util.List;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final SecurityService securityService;
    private final AccountRepository accountRepository;

    @Autowired
    public ResumeService(ResumeRepository resumeRepository, SecurityService securityService, AccountRepository accountRepository) {
        this.resumeRepository = resumeRepository;
        this.securityService = securityService;
        this.accountRepository = accountRepository;
    }

    public List<Skill> addSkill(Skill newSkill) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        if (resume == null) {
            resume = new Resume();
        }
        resume.addSkill(newSkill);
        resumeRepository.save(resume);
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        return resume.getSkills();
    }

    public List<Skill> removeSkill(int index) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        if (resume == null) {
            throw new IllegalStateException();
        }
        resume.removeSkill(index);
        resumeRepository.save(resume);
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
        if (resume == null) {
            resume = new Resume();
        }
        resume.addLanguage(newLanguage);
        resumeRepository.save(resume);
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        return resume.getLanguages();
    }

    public List<Language> removeLanguage(int index) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        if (resume == null) {
            throw new IllegalStateException();
        }
        resume.removeLanguage(index);
        resumeRepository.save(resume);
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
        if (resume == null) {
            resume = new Resume();
        }
        resume.addEducation(newEducation);
        resumeRepository.save(resume);
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        return resume.getEducationList();
    }

    public List<Education> removeEducation(int index) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        if (resume == null) {
            throw new IllegalStateException();
        }
        resume.removeEducation(index);
        resumeRepository.save(resume);
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
        if (resume == null) {
            resume = new Resume();
        }
        resume.addExperience(newExperience);
        resumeRepository.save(resume);
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        return resume.getExperienceList();
    }

    public List<Experience> removeExperience(int index) {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        if (resume == null) {
            throw new IllegalStateException();
        }
        resume.removeExperience(index);
        resumeRepository.save(resume);
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
        if (resume == null) {
            resume = new Resume();
        }
        resume.setSummary(newSummary);
        resumeRepository.save(resume);
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
        if (resume == null) {
            resume = new Resume();
        }
        resume.setStatus(newStatus);
        resumeRepository.save(resume);
        currentUserJs.setResume(resume);
        accountRepository.save(currentUserJs);
        return resume.getStatus();
    }

    public ResumeStatus getStatus() {
        return getResume().getStatus();
    }

    private Resume getResume() {
        JobSeeker currentUserJs = securityService.getJobSeeker();
        Resume resume = currentUserJs.getResume();
        if (resume == null) {
            resume = new Resume();
        }
        return resume;
    }
}
