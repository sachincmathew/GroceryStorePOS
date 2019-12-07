package com.store.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportsController {

	@RequestMapping("/reports")
	public String reports() {
		return "reports";
	}
}
