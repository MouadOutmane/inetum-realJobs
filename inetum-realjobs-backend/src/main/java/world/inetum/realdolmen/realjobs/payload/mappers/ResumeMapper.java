package world.inetum.realdolmen.realjobs.payload.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import world.inetum.realdolmen.realjobs.entities.*;
import world.inetum.realdolmen.realjobs.payload.dtos.*;

@Mapper(
        componentModel = "spring",
        uses = ResumeMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
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
