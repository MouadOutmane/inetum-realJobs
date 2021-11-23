package world.inetum.realdolmen.inetumrealJobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import world.inetum.realdolmen.inetumrealJobs.jpa.Vacancy;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
}
