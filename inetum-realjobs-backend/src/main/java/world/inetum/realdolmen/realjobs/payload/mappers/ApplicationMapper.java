package world.inetum.realdolmen.realjobs.payload.mappers;

import org.mapstruct.Mapper;
import world.inetum.realdolmen.realjobs.entities.Application;
import world.inetum.realdolmen.realjobs.payload.dtos.ApplicationReadDto;

@Mapper(config = MapperConfiguration.class)
public interface ApplicationMapper {

    ApplicationReadDto toDto(Application application);

}
