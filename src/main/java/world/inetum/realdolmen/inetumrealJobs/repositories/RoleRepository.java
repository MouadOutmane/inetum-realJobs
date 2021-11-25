package world.inetum.realdolmen.inetumrealJobs.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.inetum.realdolmen.inetumrealJobs.jpa.ERole;
import world.inetum.realdolmen.inetumrealJobs.jpa.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
