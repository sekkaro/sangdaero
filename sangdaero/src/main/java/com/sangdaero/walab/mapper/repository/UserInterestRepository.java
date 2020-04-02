package com.sangdaero.walab.mapper.repository;

import com.sangdaero.walab.mapper.entity.UserInterest;
import com.sangdaero.walab.mapper.id.UserInterestId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterestRepository extends JpaRepository<UserInterest, UserInterestId> {

    Long countByInterest_Id(Long id);
}
