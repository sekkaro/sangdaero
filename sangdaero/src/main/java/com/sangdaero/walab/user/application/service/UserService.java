package com.sangdaero.walab.user.application.service;

import com.sangdaero.walab.user.application.DTO.UserDTO;
import com.sangdaero.walab.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository mUserRepository;

    public UserService(UserRepository mUserRepository) {
        this.mUserRepository = mUserRepository;
    }

    public void addUser(UserDTO userDTO) {
    }
}
