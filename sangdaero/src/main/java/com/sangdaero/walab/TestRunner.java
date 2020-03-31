package com.sangdaero.walab;

import com.sangdaero.walab.interest.domain.entity.InterestCategory;
import com.sangdaero.walab.interest.domain.repository.InterestRepository;
import com.sangdaero.walab.user.domain.entity.User;
import com.sangdaero.walab.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class TestRunner implements ApplicationRunner {

    @Autowired
    InterestRepository interestRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        User newUser = new User();
//        newUser.setName("김준서");
//        newUser.setNickname("Loopy");
//        newUser.setPhone("010-6781-0073");
//
//        InterestCategory interest = new InterestCategory();
//        interest.setName("운전");
//        InterestCategory interest2 = new InterestCategory();
//        interest2.setName("물건전달");
//        interestRepository.save(interest);
//        interestRepository.save(interest2);
//
//        newUser.getInterests().add(interest);
//        newUser.getInterests().add(interest2);
//
//        userRepository.save(newUser);
    }
}
