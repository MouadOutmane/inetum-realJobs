package world.inetum.realdolmen.realjobs.payload.dtos;

import world.inetum.realdolmen.realjobs.entities.enums.SkillLevel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SkillCreateDto {

    @NotBlank
    private String skill;

    @NotNull
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
