package world.inetum.realdolmen.realjobs.payload.dtos;

import world.inetum.realdolmen.realjobs.entities.enums.LanguageLevel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LanguageCreateDto {

    @NotBlank
    private String language;

    @NotNull
    private LanguageLevel languageLevel;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LanguageLevel getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(LanguageLevel languageLevel) {
        this.languageLevel = languageLevel;
    }
}
