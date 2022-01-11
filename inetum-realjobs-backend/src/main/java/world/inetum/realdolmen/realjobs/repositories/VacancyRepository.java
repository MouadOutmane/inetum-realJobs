package world.inetum.realdolmen.realjobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import world.inetum.realdolmen.realjobs.entities.Vacancy;

import java.util.List;
import java.util.Optional;

public interface VacancyRepository extends JpaRepository<Vacancy, Long>, JpaSpecificationExecutor<Vacancy> {

    @Override
    @Query(value = "SELECT v FROM Vacancy v JOIN FETCH v.address a JOIN FETCH a.country LEFT JOIN FETCH v.company c LEFT JOIN FETCH c.country WHERE v.id = :id")
    Optional<Vacancy> findById(Long id);

    List<Vacancy> findVacanciesByRecruiter_Id(Long recruiterId);
}
