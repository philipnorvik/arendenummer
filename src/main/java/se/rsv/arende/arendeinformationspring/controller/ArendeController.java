package se.rsv.arende.arendeinformationspring.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se.rsv.arende.arendeinformationspring.exception.FelMyndighetException;
import se.rsv.arende.arendeinformationspring.service.ArendeNrService;

@Controller
@RequestMapping("/arende")
public class ArendeController {

	@Autowired
	ArendeNrService arendeNrService;

	@PostMapping("/arende")
	public ResponseEntity post(String myndighet) {
		arendeNrService.createArende(myndighet);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/valAvMyndighet")
	public ResponseEntity<String> valAvMyndighet(String myndighet) {
		try {
			String arendenummer = arendeNrService.valAvMyndighet(myndighet);
		} catch (FelMyndighetException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
