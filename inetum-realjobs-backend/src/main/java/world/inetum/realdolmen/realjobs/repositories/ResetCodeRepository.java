package world.inetum.realdolmen.realjobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import world.inetum.realdolmen.realjobs.entities.ResetCode;

import java.util.UUID;

public interface ResetCodeRepository extends JpaRepository<ResetCode, UUID> {

    void deleteAllByAccountId(long id);

}
