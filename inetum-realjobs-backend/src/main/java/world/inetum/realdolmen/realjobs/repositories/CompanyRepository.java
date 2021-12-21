package world.inetum.realdolmen.realjobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import world.inetum.realdolmen.realjobs.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
