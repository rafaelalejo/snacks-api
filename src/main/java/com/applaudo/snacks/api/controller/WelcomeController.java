package com.applaudo.snacks.api.controller;

import com.applaudo.snacks.api.response.CustomMessageResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	@GetMapping("/")
	public CustomMessageResponse hello() {
		return new CustomMessageResponse("Hola Applaudo?");
	}
}