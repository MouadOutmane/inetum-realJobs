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

}