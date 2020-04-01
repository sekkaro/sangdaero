package com.sangdaero.walab.user.controller;

import com.sangdaero.walab.interest.application.DTO.InterestDTO;
import com.sangdaero.walab.interest.application.service.InterestService;
import com.sangdaero.walab.user.application.DTO.SimpleUser;
import com.sangdaero.walab.user.application.DTO.UserDTO;
import com.sangdaero.walab.user.application.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserService mUserService;
	private InterestService mInterestService;

	public UserController(UserService mUserService, InterestService mInterestService) {
		this.mUserService = mUserService;
		this.mInterestService = mInterestService;
	}

	@GetMapping("")
	public String userPage(Model model) {
		List<SimpleUser> simpleUsers = mUserService.getSimpleUserList();
		model.addAttribute("simpleUserList", simpleUsers);
		return "html/user/user.html";
	}

	@GetMapping("/add")
	public String add(Model model) {
		List<InterestDTO> interestDTOList = mInterestService.getInterestList();
		model.addAttribute("interestList", interestDTOList);
		return "html/user/add.html";
	}

	@PostMapping("/add")
	public String addUser(UserDTO userDTO) {
		mUserService.addUser(userDTO);
		return "redirect:/user";
	}


}
