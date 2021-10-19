package com.kk.secutiry.SpringSecutiry.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicAuthenticationController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello from Spring security !!";
	}
	
	@GetMapping("/bye")
	public String bye() {
		return "Get Lost !!";
	}
}
