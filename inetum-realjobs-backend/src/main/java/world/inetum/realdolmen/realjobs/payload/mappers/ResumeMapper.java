package world.inetum.realdolmen.realjobs.payload.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import world.inetum.realdolmen.realjobs.entities.*;
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

    Experience toEntity(ExperienceEditDto experienceEditDto);

    @Mapping(source = "account", target = "accountInfo")
    ResumeReadDto toDto(Resume resume, Account account);
}
