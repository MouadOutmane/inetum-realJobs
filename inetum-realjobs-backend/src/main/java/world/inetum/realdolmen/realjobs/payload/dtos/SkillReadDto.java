package world.inetum.realdolmen.realjobs.payload.dtos;

import world.inetum.realdolmen.realjobs.entities.enums.SkillLevel;

public class SkillReadDto {

    private Long id;
    private String skill;
    private SkillLevel skillLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
