package com.store.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
	
	@RequestMapping("/healthCheck")
	public String healthCheck() {
		return "Ok";
	}

}
