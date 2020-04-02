package com.sangdaero.walab.user.domain.repository;

import com.sangdaero.walab.user.application.DTO.SimpleUser;
import com.sangdaero.walab.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<SimpleUser> findAllByOrderByName();
}
