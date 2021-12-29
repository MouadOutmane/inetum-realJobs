package world.inetum.realdolmen.realjobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import world.inetum.realdolmen.realjobs.entities.JobSeeker;

import java.util.Optional;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {

    @Query("select j from JobSeeker j join fetch j.resume where j.email = :email")
    Optional<JobSeeker> findByEmailWithResume(@Param("email") String email);
}
