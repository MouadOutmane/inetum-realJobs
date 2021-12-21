package world.inetum.realdolmen.inetumrealJobs.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import world.inetum.realdolmen.inetumrealJobs.dtos.VacancyFilterResultDto;
import world.inetum.realdolmen.inetumrealJobs.entities.Vacancy;

@Mapper(componentModel = "cdi", uses = VacancyFilterResultMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface VacancyFilterResultMapper {

    VacancyFilterResultDto toDto(Vacancy vacancy);

}
