package se.rsv.arende.arendeinformationspring;

import org.springframework.data.jpa.repository.Query;
import se.rsv.arende.arendeinformationspring.model.Arende;
import java.util.List;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ArendeRepository extends CrudRepository<Arende, Long>{
	
	List<Arende> findByMyndighet(String myndighet);
	
	Arende findById(long id);

	@Query("select a from Arende a where a.arendenummer = ?1")
	Arende findByArendenummer(String arendenummer);
}
