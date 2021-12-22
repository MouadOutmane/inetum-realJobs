package world.inetum.realdolmen.realjobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import world.inetum.realdolmen.realjobs.entities.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

}
