package world.inetum.realdolmen.realjobs.payload.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import world.inetum.realdolmen.realjobs.entities.Vacancy;
import world.inetum.realdolmen.realjobs.payload.dtos.VacancyReadDto;

@Mapper(config = MapperConfiguration.class)
public interface VacancyMapper {

    @Mapping(source = "address.city", target = "city")
    @Mapping(source = "address.streetName", target = "streetName")
    @Mapping(source = "address.houseNumber", target = "houseNumber")
    @Mapping(source = "address.box", target = "box")
    @Mapping(source = "address.postalCode", target = "postalCode")
    @Mapping(source = "address.country.name", target = "country")
    @Mapping(source = "company.name", target = "companyName")
    @Mapping(source = "company.name", target = "companyIndustry")
    @Mapping(source = "company.country.name", target = "companyCountry")
    VacancyReadDto toDto(Vacancy vacancy);

}
