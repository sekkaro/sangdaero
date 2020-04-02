package com.sangdaero.walab.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sangdaero.walab.common.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findBySocialId(String socialId);
}
