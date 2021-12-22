package world.inetum.realdolmen.realjobs.payload.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import world.inetum.realdolmen.realjobs.payload.dtos.VacancyReadDto;
import world.inetum.realdolmen.realjobs.entities.Vacancy;

@Mapper(
        componentModel = "spring",
        uses = VacancyMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface VacancyMapper {

    VacancyReadDto toDto(Vacancy vacancy);

}
