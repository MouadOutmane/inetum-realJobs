package world.inetum.realdolmen.realjobs.entities;

import world.inetum.realdolmen.realjobs.entities.enums.SkillLevel;

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
    @Column(name = "skill_level", nullable = false)
    private SkillLevel skillLevel;

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
}
