package rocks.zipcode.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import rocks.zipcode.domain.Log;

/**
 * Spring Data JPA repository for the Log entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LogRepository extends JpaRepository<Log, Long> {}
