package world.inetum.realdolmen.realjobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import world.inetum.realdolmen.realjobs.entities.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT A FROM Account A JOIN FETCH A.address as AD JOIN FETCH AD.country WHERE A.email  =?1")
    Optional<Account> getPersonalInformationByEmail(String email);

}