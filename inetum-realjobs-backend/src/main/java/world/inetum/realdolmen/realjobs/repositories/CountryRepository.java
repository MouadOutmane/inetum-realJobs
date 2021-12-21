package world.inetum.realdolmen.realjobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import world.inetum.realdolmen.realjobs.entities.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByName(String name);

    List<Country> findAllByOrderByName();

}
