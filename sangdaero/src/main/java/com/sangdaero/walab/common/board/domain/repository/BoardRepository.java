package com.sangdaero.walab.common.board.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sangdaero.walab.common.board.domain.entity.CommonBoard;

public interface BoardRepository extends JpaRepository<CommonBoard, Long> {
	List<CommonBoard> findByTitleContaining(String keyword);
}
