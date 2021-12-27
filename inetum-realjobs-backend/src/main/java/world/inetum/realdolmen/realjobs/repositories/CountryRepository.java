package world.inetum.realdolmen.realjobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.inetum.realdolmen.realjobs.entities.Country;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long>{

    Optional<Country> findByName(String name);
}
