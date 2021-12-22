package world.inetum.realdolmen.realjobs.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import world.inetum.realdolmen.realjobs.payload.dtos.VacancyReadDto;
import world.inetum.realdolmen.realjobs.entities.Vacancy;

@Mapper(componentModel = "cdi", uses = VacancyFilterResultMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface VacancyFilterResultMapper {

    VacancyReadDto toDto(Vacancy vacancy);

}
