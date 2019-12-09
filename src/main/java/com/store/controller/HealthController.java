package com.store.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"Health Check(s)"})
@RestController
@RequestMapping("/api")
public class HealthController {

	@ApiOperation(value = "Confirms that the API system is up and responsive")
	@RequestMapping(path = "/healthCheck", method = RequestMethod.GET)
	public String healthCheck() {
		return "Ok";
	}

}
