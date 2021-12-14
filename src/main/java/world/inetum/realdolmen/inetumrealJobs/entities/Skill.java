package world.inetum.realdolmen.inetumrealJobs.entities;

import world.inetum.realdolmen.inetumrealJobs.entities.enums.SkillLevel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Skill")
public class Skill extends BaseModel {

    @NotBlank
    @Column(name = "skill", nullable = false)
    private String skill;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "program", nullable = false)
    private SkillLevel skillLevel;

    @JoinColumn(
            name = "resume_id"
    )
    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
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
