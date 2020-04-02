package com.sangdaero.walab.interest.domain.repository;

import com.sangdaero.walab.interest.application.DTO.InterestName;
import com.sangdaero.walab.interest.domain.entity.InterestCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface InterestRepository extends JpaRepository<InterestCategory, Long> {
    List<InterestCategory> findByTypeEquals(Byte type);

    InterestCategory findByNameEquals(String name);

    List<InterestName> findAllByOrderByName();

}
