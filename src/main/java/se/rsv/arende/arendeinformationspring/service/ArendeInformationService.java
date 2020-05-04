package se.rsv.arende.arendeinformationspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.rsv.arende.arendeinformationspring.ArendeRepository;
import se.rsv.arende.arendeinformationspring.model.Arende;

@Service
public class ArendeInformationService {
	
	@Autowired
	private  ArendeRepository arendeRepo;
	
	public List<Arende> findByMyndighet (String myndighet) {
		return arendeRepo.findByMyndighet(myndighet);
		
	}
	public Arende findById (long id){
		return arendeRepo.findById(id);
	}
	
	public Arende save(Arende a) {
		return arendeRepo.save(a);
	}

}
