package com.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.store.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"Report API(s)"})
@RestController
@RequestMapping("/api/reports")
public class ReportsController {

	@Autowired
	private ReportService reportService;

	@ApiOperation(value = "Tally's all sold items in **closed** shopping carts, and computes the total profit or loss for last specified number of days")
	@RequestMapping(path = "/calculateProfit/{days}", method = RequestMethod.GET)
	public String calculateProfit(@PathVariable int days) {
		if(days <= 0) {
			return "invalid day count entered";
		}
		return reportService.calculateProfit(days).toString();
	}
}
