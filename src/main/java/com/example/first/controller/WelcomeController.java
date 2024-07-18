package com.example.first.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WelcomeController {
	@GetMapping
	public String welcome(){
		return "Welcome to Employee backend service";
	}
	@GetMapping("/health")
	public String health(){
		return "this is the health endpoint";
	}

}
