package ma.akamal.atos.repository;

import ma.akamal.atos.domain.JobExecution;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the JobExecution entity.
 */
@SuppressWarnings("unused")
public interface JobExecutionRepository extends JpaRepository<JobExecution,Long> {

}
