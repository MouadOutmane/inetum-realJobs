package world.inetum.realdolmen.realjobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import world.inetum.realdolmen.realjobs.entities.JobSeeker;
import world.inetum.realdolmen.realjobs.entities.Recruiter;

import java.util.Optional;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {

    @Query("select r from Recruiter r join fetch r.company where r.email = :email")
    Optional<Recruiter> findByEmailWithCompany(@Param("email") String email);
}
