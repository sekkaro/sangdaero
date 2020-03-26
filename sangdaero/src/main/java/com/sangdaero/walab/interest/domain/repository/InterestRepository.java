package com.sangdaero.walab.interest.domain.repository;

import com.sangdaero.walab.interest.domain.entity.InterestCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestRepository extends JpaRepository<InterestCategory, Long> {
    List<InterestCategory> findByTypeEquals(Byte type);
}
