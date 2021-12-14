package world.inetum.realdolmen.inetumrealJobs.entities;

import world.inetum.realdolmen.inetumrealJobs.entities.enums.SkillLevel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Language")
public class Language extends BaseModel {

    @NotBlank
    @Column(name = "language", nullable = false)
    private String language;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "program", nullable = false)
    private SkillLevel skillLevel;

    @JoinColumn(
            name = "resume_id"
    )
    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public SkillLevel getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SkillLevel skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }
}
