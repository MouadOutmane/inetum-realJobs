package world.inetum.realdolmen.realjobs.payload.dtos;

import world.inetum.realdolmen.realjobs.entities.enums.ResumeStatus;

import java.util.List;

public class ResumeReadDto {

    private Long id;

    private String summary;

    private ResumeStatus status;

    private List<SkillDto> skills;

    private List<LanguageDto> languages;

    private List<EducationDto> educationList;

    private List<ExperienceDto> experienceList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<SkillDto> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillDto> skills) {
        this.skills = skills;
    }

    public List<LanguageDto> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageDto> languages) {
        this.languages = languages;
    }

    public List<EducationDto> getEducationList() {
        return educationList;
    }

    public void setEducationList(List<EducationDto> educationList) {
        this.educationList = educationList;
    }

    public List<ExperienceDto> getExperienceList() {
        return experienceList;
    }

    public void setExperienceList(List<ExperienceDto> experienceList) {
        this.experienceList = experienceList;
    }
}
