package world.inetum.realdolmen.realjobs.entities;

import world.inetum.realdolmen.realjobs.entities.enums.ResumeStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Resume")
public class Resume extends BaseModel {

    @Column(name = "summary")
    private String summary;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ResumeStatus status;

    // TODO: 14-Dec-21 email: this can come from Account

    // TODO: 14-Dec-21 mobilePhone: this can also come from account

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "resume_id")
    private List<Skill> skills = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "resume_id")
    private List<Language> languages = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "resume_id")
    private List<Education> educationList = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "resume_id")
    private List<Experience> experienceList = new ArrayList<>();

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public void addLanguage(Language language) {
        languages.add(language);
    }

    public void addEducation(Education education) {
        educationList.add(education);
    }

    public void addExperience(Experience experience) {
        experienceList.add(experience);
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

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Education> getEducationList() {
        return educationList;
    }

    public void setEducationList(List<Education> educationList) {
        this.educationList = educationList;
    }

    public List<Experience> getExperienceList() {
        return experienceList;
    }

    public void setExperienceList(List<Experience> experienceList) {
        this.experienceList = experienceList;
    }
}
