package world.inetum.realdolmen.inetumrealJobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import world.inetum.realdolmen.inetumrealJobs.entities.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
