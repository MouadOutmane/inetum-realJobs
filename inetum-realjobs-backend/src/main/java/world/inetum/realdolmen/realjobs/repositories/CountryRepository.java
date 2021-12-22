package world.inetum.realdolmen.inetumrealJobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.inetum.realdolmen.inetumrealJobs.entities.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long>{
}
