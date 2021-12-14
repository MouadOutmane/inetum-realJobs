package world.inetum.realdolmen.inetumrealJobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.inetum.realdolmen.inetumrealJobs.entities.ERole;
import world.inetum.realdolmen.inetumrealJobs.entities.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
