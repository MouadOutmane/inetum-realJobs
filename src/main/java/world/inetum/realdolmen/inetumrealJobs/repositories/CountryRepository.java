package world.inetum.realdolmen.inetumrealJobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import world.inetum.realdolmen.inetumrealJobs.entities.Country;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByName(String name);

}
