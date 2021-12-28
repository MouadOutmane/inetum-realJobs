package world.inetum.realdolmen.realjobs.payload.mappers;


import org.mapstruct.Mapper;
import world.inetum.realdolmen.realjobs.entities.Account;
import world.inetum.realdolmen.realjobs.entities.Address;
import world.inetum.realdolmen.realjobs.entities.Country;
import world.inetum.realdolmen.realjobs.payload.dtos.AccountReadDto;
import world.inetum.realdolmen.realjobs.payload.dtos.AddressReadDto;

@Mapper(config = MapperConfiguration.class)
public abstract class AddressMapper {
    abstract AddressReadDto toDto(Address address);

    Long map(Country value) {
        return value.getId();
    }
}
