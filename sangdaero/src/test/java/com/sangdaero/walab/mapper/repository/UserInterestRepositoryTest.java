package com.sangdaero.walab.mapper.repository;

import com.sangdaero.walab.interest.domain.entity.InterestCategory;
import com.sangdaero.walab.interest.domain.repository.InterestRepository;
import com.sangdaero.walab.mapper.entity.UserInterest;
import com.sangdaero.walab.user.domain.entity.User;
import com.sangdaero.walab.user.domain.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserInterestRepositoryTest {

    @Autowired
    UserInterestRepository repository;

    @Autowired
    InterestRepository interestRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void count() {
        InterestCategory category = new InterestCategory();
        category.setName("운전");
        InterestCategory category1 = new InterestCategory();
        category1.setName("나눔");

        interestRepository.save(category);
        interestRepository.save(category1);

        User user = new User();
        user.setName("준서");
        User user1 = new User();
        user1.setName("준범");

        userRepository.save(user);
        userRepository.save(user1);

        UserInterest userInterest = new UserInterest();
        userInterest.setInterest(category);
        userInterest.setUser(user);

        UserInterest userInterest1 = new UserInterest();
        userInterest1.setInterest(category1);
        userInterest1.setUser(user);

        UserInterest userInterest2 = new UserInterest();
        userInterest2.setInterest(category);
        userInterest2.setUser(user1);

        repository.save(userInterest);
        repository.save(userInterest1);
        repository.save(userInterest2);

        Long cnt = repository.countByInterest_Id(category.getId());
        System.out.println(cnt);
    }

    @Test
    public void test() {
        InterestCategory category = new InterestCategory();
        category.setName("운전");
        InterestCategory category1 = new InterestCategory();
        category1.setName("나눔");

        interestRepository.save(category);
        interestRepository.save(category1);

        User user = new User();
        user.setName("준서");
        User user1 = new User();
        user1.setName("준범");

        userRepository.save(user);
        userRepository.save(user1);

        UserInterest userInterest = new UserInterest();
        userInterest.setInterest(category);
        userInterest.setUser(user);

        UserInterest userInterest1 = new UserInterest();
        userInterest1.setInterest(category1);
        userInterest1.setUser(user);

        UserInterest userInterest2 = new UserInterest();
        userInterest2.setInterest(category);
        userInterest2.setUser(user1);

        repository.save(userInterest);
        repository.save(userInterest1);
        repository.save(userInterest2);

        List<UserInterest> all = repository.findAll();

        System.out.println("================================");
        for(UserInterest value : all) {
            System.out.println(value.getUser().getName() + " " + value.getInterest().getName());
        }
        System.out.println("================================");
    }
}