package world.inetum.realdolmen.realjobs.payload.dtos;

import world.inetum.realdolmen.realjobs.entities.enums.ResumeStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ResumeCreationDto {

    // TODO set max length
    @NotBlank
    private String summary;

    @NotNull
    private ResumeStatus status;

    private List<SkillDto> skills;

    private List<LanguageDto> languages;

    private List<EducationDto> educationList;

    private List<ExperienceDto> experienceList;

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
