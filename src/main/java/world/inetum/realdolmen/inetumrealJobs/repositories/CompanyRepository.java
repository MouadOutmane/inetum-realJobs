package world.inetum.realdolmen.inetumrealJobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import world.inetum.realdolmen.inetumrealJobs.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
