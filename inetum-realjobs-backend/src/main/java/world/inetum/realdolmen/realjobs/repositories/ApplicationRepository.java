package world.inetum.realdolmen.realjobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import world.inetum.realdolmen.realjobs.entities.Application;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("SELECT a FROM Application a JOIN FETCH a.vacancy v WHERE v.id = :id")
    List<Application> findAllByVacancyId(long id);

//    @Query("select count (vacancy_id) from Application a where a.vacancy.id = :id")
    Integer countApplicationsByVacancy_Id(Long id);
}