package com.sangdaero.walab.user.application.service;

import com.sangdaero.walab.interest.domain.entity.InterestCategory;
import com.sangdaero.walab.interest.domain.repository.InterestRepository;
import com.sangdaero.walab.user.application.DTO.UserDTO;
import com.sangdaero.walab.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private InterestRepository mInterestRepository;
    private UserRepository mUserRepository;

    public UserService(UserRepository mUserRepository, InterestRepository mInterestRepository) {
        this.mUserRepository = mUserRepository;
        this.mInterestRepository = mInterestRepository;
    }

    public void addUser(UserDTO userDTO) {
        for (String e :userDTO.getUserInterestList()) {
            InterestCategory interestList = mInterestRepository.findByNameEquals(e);
            userDTO.getInterests().add(interestList);
        }
        mUserRepository.save(userDTO.toEntity()).getId();
    }
}
