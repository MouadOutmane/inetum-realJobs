package world.inetum.realdolmen.realjobs.payload.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import world.inetum.realdolmen.realjobs.entities.Resume;
import world.inetum.realdolmen.realjobs.payload.dtos.ResumeDto;

@Mapper(
        componentModel = "spring",
        uses = ResumeMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ResumeMapper {

    ResumeDto toDto(Resume resume);

    Resume toEntity(ResumeDto resumeDto);
}
