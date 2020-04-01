package com.sangdaero.walab.user.domain.repository;

import com.sangdaero.walab.interest.domain.entity.InterestCategory;
import com.sangdaero.walab.user.application.DTO.SimpleUser;
import com.sangdaero.walab.user.domain.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void getSimpleUser() {
        User user = new User();
        user.setName("준서");
        user.setNickname("loopy");
        user.setUserType((byte) 1);
        userRepository.save(user);

        List<SimpleUser> users = userRepository.findAllByOrderByName();

    }
}