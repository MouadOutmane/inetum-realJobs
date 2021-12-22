package world.inetum.realdolmen.realjobs.payload.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import world.inetum.realdolmen.realjobs.entities.Resume;
import world.inetum.realdolmen.realjobs.payload.dtos.ResumeCreationDto;
import world.inetum.realdolmen.realjobs.payload.dtos.ResumeReadDto;

@Mapper(
        componentModel = "spring",
        uses = ResumeMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ResumeMapper {

    ResumeReadDto toDto(Resume resume);

    Resume toEntity(ResumeCreationDto resumeCreationDto);
}
