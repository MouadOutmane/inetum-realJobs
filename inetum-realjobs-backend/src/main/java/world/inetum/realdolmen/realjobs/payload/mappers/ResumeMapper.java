package world.inetum.realdolmen.realjobs.payload.mappers;

import org.mapstruct.Mapper;
import world.inetum.realdolmen.realjobs.entities.Education;
import world.inetum.realdolmen.realjobs.entities.Experience;
import world.inetum.realdolmen.realjobs.entities.Language;
import world.inetum.realdolmen.realjobs.entities.Skill;
import world.inetum.realdolmen.realjobs.payload.dtos.*;

@Mapper(config = MapperConfiguration.class)
public interface ResumeMapper {

    SkillReadDto toDto(Skill skill);

    Skill toEntity(SkillCreateDto skillCreateDto);

    LanguageReadDto toDto(Language language);

    Language toEntity(LanguageCreateDto languageCreateDto);

    EducationReadDto toDto(Education education);

    Education toEntity(EducationCreateDto educationCreateDto);

    ExperienceReadDto toDto(Experience experience);

    Experience toEntity(ExperienceCreateDto experienceCreateDto);
}
