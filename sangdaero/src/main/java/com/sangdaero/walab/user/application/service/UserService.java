package com.sangdaero.walab.user.application.service;

import com.sangdaero.walab.interest.domain.entity.InterestCategory;
import com.sangdaero.walab.interest.domain.repository.InterestRepository;
import com.sangdaero.walab.mapper.entity.UserInterest;
import com.sangdaero.walab.mapper.repository.UserInterestRepository;
import com.sangdaero.walab.user.application.DTO.SimpleUser;
import com.sangdaero.walab.user.application.DTO.UserDTO;
import com.sangdaero.walab.user.domain.entity.User;
import com.sangdaero.walab.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private InterestRepository mInterestRepository;
    private UserRepository mUserRepository;
    private UserInterestRepository mUserInterestRepository;

    public UserService(UserRepository mUserRepository, InterestRepository mInterestRepository, UserInterestRepository mUserInterestRepository) {
        this.mUserRepository = mUserRepository;
        this.mInterestRepository = mInterestRepository;
        this.mUserInterestRepository = mUserInterestRepository;
    }

    public void addUser(UserDTO userDTO) {

        for (String e :userDTO.getUserInterestList()) {
            InterestCategory interestList = mInterestRepository.findByNameEquals(e);
            userDTO.getInterests().add(interestList);
        }

        User user = mUserRepository.save(userDTO.toEntity());

        for(InterestCategory interest : userDTO.getInterests()) {
            UserInterest userInterest = new UserInterest();

            userInterest.setInterest(interest);
            userInterest.setUser(user);

            mUserInterestRepository.save(userInterest);
        }
    }


    public List<SimpleUser> getSimpleUserList() {
        List<SimpleUser> simpleUserList = mUserRepository.findAllByOrderByName();
        return simpleUserList;
    }


}
