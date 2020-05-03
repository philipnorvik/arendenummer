package se.rsv.arende.arendeinformationspring.controller;

import java.util.Map;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.rsv.arende.arendeinformationspring.model.Arende;
import se.rsv.arende.arendeinformationspring.service.ArendeInformationService;
import se.rsv.arende.arendeinformationspring.service.ArendeNrService;

@Controller
public class MainController {

	@Autowired
	ArendeInformationService service;

	@Autowired
	ArendeNrService arendeNrService;
	
	@GetMapping("/")
	public String Index() {
		return "login";
	}
	// Login form
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	// Login form with error
	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login.html";
	}

	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("arende", new Arende());
		return "register";
	}

	@PostMapping(path = "/register" /* , method = RequestMethod.POST */)
	public String registerSubmit(@ModelAttribute Arende arende) {
		service.save(arende);
		return "arendenummer";

	}

}
