package world.inetum.realdolmen.realjobs.payload.dtos;

import world.inetum.realdolmen.realjobs.entities.enums.ResumeStatus;

import java.util.List;

public class ResumeReadDto {

    private AccountResumeReadDto accountInfo;
    private String summary;
    private ResumeStatus status;
    private List<SkillReadDto> skills;
    private List<LanguageReadDto> languages;
    private List<EducationReadDto> educationList;
    private List<ExperienceReadDto> experienceList;

    public AccountResumeReadDto getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountResumeReadDto accountInfo) {
        this.accountInfo = accountInfo;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ResumeStatus getStatus() {
        return status;
    }

    public void setStatus(ResumeStatus status) {
        this.status = status;
    }

    public List<SkillReadDto> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillReadDto> skills) {
        this.skills = skills;
    }

    public List<LanguageReadDto> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageReadDto> languages) {
        this.languages = languages;
    }

    public List<EducationReadDto> getEducationList() {
        return educationList;
    }

    public void setEducationList(List<EducationReadDto> educationList) {
        this.educationList = educationList;
    }

    public List<ExperienceReadDto> getExperienceList() {
        return experienceList;
    }

    public void setExperienceList(List<ExperienceReadDto> experienceList) {
        this.experienceList = experienceList;
    }
}
