package se.rsv.arende.arendeinformationspring.controller;

import java.io.IOException;
import java.util.Locale;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se.rsv.arende.arendeinformationspring.exception.FelMyndighetException;
import se.rsv.arende.arendeinformationspring.service.ArendeNrService;

@Controller
@RequestMapping("v0/api/arende")
@Api(value="Arendehantering API", description="Arende hantering av myndigheter")

public class ArendeController {

	@Autowired
	ArendeNrService arendeNrService;


	@GetMapping("/")
	@ApiOperation(value = "hämta ärende genom ärende nummer", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "hittat arende"),
			@ApiResponse(code = 401, message = "Du är inte inloggad"),
			@ApiResponse(code = 500, message = "Myndighet finns inte"),
	})
	public ResponseEntity get(String arendenr) {
		arendeNrService.getArende(arendenr);
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@GetMapping("/list")
	@ApiOperation(value = "hämta alla ärende genom ärende nummer", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "hittat arende"),
			@ApiResponse(code = 401, message = "Du är inte inloggad"),
			@ApiResponse(code = 500, message = "Myndighet finns inte"),
	})
	public ResponseEntity getList() {
		return new ResponseEntity<>(arendeNrService.getArenden(),HttpStatus.OK);
	}



	@PostMapping("/")
	@ApiOperation(value = "Skapa ärende på inskickad myndighet", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Skapat arende"),
			@ApiResponse(code = 401, message = "Du är inte inloggad"),
			@ApiResponse(code = 500, message = "Myndighet finns inte"),
	})


	public ResponseEntity post(String myndighet) {
		try {
			arendeNrService.createArende(myndighet);
		} catch (IOException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (FelMyndighetException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/valAvMyndighet")
	@ApiOperation(value = "Skapa ärende på inskickad myndighet", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "vald myndighet"),
			@ApiResponse(code = 401, message = "Du är inte inloggad"),
			@ApiResponse(code = 500, message = "Myndighet finns inte"),
	})
	public ResponseEntity<String> valAvMyndighet(String myndighet) {
		String arendenummer;
		try {
			arendenummer = arendeNrService.valAvMyndighet(myndighet);
		} catch (FelMyndighetException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(arendenummer,HttpStatus.OK);
	}
}
