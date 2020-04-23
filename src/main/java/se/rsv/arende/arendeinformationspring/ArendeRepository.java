package se.rsv.arende.arendeinformationspring;

import se.rsv.arende.arendeinformationspring.model.Arende;
import java.util.List;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ArendeRepository extends CrudRepository<Arende, Long>{
	
	List<Arende> findByMyndighet(String myndighet);
	
	Arende findById(long id);
	
}
