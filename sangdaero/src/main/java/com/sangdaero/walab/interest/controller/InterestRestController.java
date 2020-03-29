package com.sangdaero.walab.interest.controller;

import com.sangdaero.walab.interest.application.DTO.InterestDTO;
import com.sangdaero.walab.interest.application.service.InterestService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interest")
public class InterestRestController {

    private InterestService mInterestService;

    public InterestRestController(InterestService mInterestService) {
        this.mInterestService = mInterestService;
    }

//    @PutMapping("/edit/{id}")
//    public void update(InterestDTO interestDTO) {
//        mInterestService.addInterest(interestDTO);
//    }

}
