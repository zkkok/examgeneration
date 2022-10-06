package edu.deakin.sit218.examgeneration.controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	

	@GetMapping("/")
	public String showHome() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		final Logger LOGGER = Logger.getLogger(HomeController.class);
		LOGGER.info(username + " Successfully logged in.");
		      
		return "home";
	}
}
