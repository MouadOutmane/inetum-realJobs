package world.inetum.realdolmen.realjobs.entities;

import world.inetum.realdolmen.realjobs.entities.enums.LanguageLevel;

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
    @Column(name = "language_level", nullable = false)
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

    @Override
    public String toString() {
        return """
                Language{id=%d, language='%s', skillLevel=%s}
                """.formatted(getId(), language, languageLevel);
    }
}
