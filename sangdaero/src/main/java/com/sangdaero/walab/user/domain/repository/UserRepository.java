package com.sangdaero.walab.user.domain.repository;

import com.sangdaero.walab.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
