package world.inetum.realdolmen.realjobs.payload.mappers;


import org.mapstruct.Mapper;
import world.inetum.realdolmen.realjobs.entities.Account;
import world.inetum.realdolmen.realjobs.entities.Country;
import world.inetum.realdolmen.realjobs.payload.dtos.AccountReadDto;

@Mapper(config = MapperConfiguration.class)
public abstract class ProfileMapper {
    public abstract AccountReadDto toDto(Account account);

    Long map(Country value) {
        return value.getId();
    }
}
