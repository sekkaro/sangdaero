package com.sangdaero.walab.interest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InterestController {
	
	@GetMapping("/interest")
	public String interestPage() {
		return "html/interest.html";
	}

}