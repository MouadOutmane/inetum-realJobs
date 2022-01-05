package world.inetum.realdolmen.realjobs.payload.mappers;

import org.mapstruct.Mapper;
import world.inetum.realdolmen.realjobs.entities.Account;
import world.inetum.realdolmen.realjobs.payload.dtos.AccountResumeReadDto;

@Mapper(config = MapperConfiguration.class)
public interface AccountMapper {

    AccountResumeReadDto toDto(Account account);

}
