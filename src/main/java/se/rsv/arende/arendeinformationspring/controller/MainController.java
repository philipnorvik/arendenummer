package se.rsv.arende.arendeinformationspring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.rsv.arende.arendeinformationspring.model.Arende;
import se.rsv.arende.arendeinformationspring.service.ArendeInformationService;


@Controller
public class RegisteringController {
	
	@Autowired
	ArendeInformationService service;
	
	@GetMapping("/register")
	public String registerForm (Model model) {
		model.addAttribute("arende", new Arende());
		return "register";
	}
	
	
	@PostMapping(path = "/register"  /*, method = RequestMethod.POST*/)
	public String registerSubmit(@ModelAttribute Arende arende) {
		service.save(arende);	
		return "arendenummer";
		
	}

}
