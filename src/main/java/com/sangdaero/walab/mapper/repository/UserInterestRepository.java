package com.sangdaero.walab.mapper.repository;

import com.sangdaero.walab.interest.domain.entity.InterestCategory;
import com.sangdaero.walab.mapper.entity.UserInterest;
import com.sangdaero.walab.mapper.id.UserInterestId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserInterestRepository extends JpaRepository<UserInterest, UserInterestId> {

    Long countByInterest_Id(Long id);

    List<UserInterest> findByUser_Id(Long id);

}
