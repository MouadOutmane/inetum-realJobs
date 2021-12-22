package world.inetum.realdolmen.realjobs.payload.dtos;

import world.inetum.realdolmen.realjobs.entities.enums.SkillLevel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LanguageDto {

    @NotBlank
    private String language;

    @NotNull
    private SkillLevel skillLevel;

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
}
