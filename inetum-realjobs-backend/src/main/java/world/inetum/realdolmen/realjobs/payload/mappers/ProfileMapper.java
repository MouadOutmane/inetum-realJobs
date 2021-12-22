package world.inetum.realdolmen.realjobs.payload.mappers;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import world.inetum.realdolmen.realjobs.entities.Account;
import world.inetum.realdolmen.realjobs.payload.dtos.ProfileDto;

@Mapper(
        componentModel = "spring",
        uses = ProfileMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProfileMapper {

    ProfileDto toDto(Account account);
}
