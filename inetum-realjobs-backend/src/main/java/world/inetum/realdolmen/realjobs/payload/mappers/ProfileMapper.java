package world.inetum.realdolmen.realjobs.payload.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import world.inetum.realdolmen.realjobs.entities.Account;
import world.inetum.realdolmen.realjobs.entities.Country;
import world.inetum.realdolmen.realjobs.payload.dtos.AccountReadDto;

@Mapper(config = MapperConfiguration.class)
public interface ProfileMapper {

    @Mapping(source = "address.country.name", target = "address.country")
    AccountReadDto toDto(Account account);
}
