package com.sangdaero.walab.interest.controller;

import com.sangdaero.walab.interest.application.DTO.InterestDTO;
import com.sangdaero.walab.interest.application.service.InterestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/interest")
public class InterestController {

	private InterestService mInterestService;

	public InterestController(InterestService interestService) {
		this.mInterestService = interestService;
	}

	@GetMapping("")
	public String interestPage(@RequestParam(required = false) Byte type, Model model) {
		List<InterestDTO> interestDTOList = mInterestService.getInterestList(type);
		model.addAttribute("interestList", interestDTOList);
		return "html/interest/interest.html";
	}

	@GetMapping("/add")
	public String add() {
		return "html/interest/add.html";
	}

	@PostMapping("/add")
	public String addInterest(InterestDTO interestDto) {
		System.out.println(interestDto);
		mInterestService.addInterest(interestDto);
		return "redirect:/interest";
	}

}