package com.sangdaero.walab.community.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sangdaero.walab.common.entity.Board;

public interface CommunityRepository extends JpaRepository<Board, Long> {
	
	// Get communities without deleted
	Page<Board> findAllBySubCategoryNotAndTopCategoryEquals(Long subCategory, Byte topCategory, Pageable page);
	// Get communities which fit to subCategory
	Page<Board> findAllBySubCategoryAndTopCategoryEquals(Long subCategory, Byte topCategory, Pageable page);
	// Searching communities without deleted
	Page<Board> findAllByTitleContainingAndSubCategoryNotAndTopCategoryEquals(String title, Long subCategory, Byte topCategory, Pageable page);
	// Searching communities which fit to subCategory
	Page<Board> findAllByTitleContainingAndSubCategoryAndTopCategoryEquals(String title, Long subCategory, Byte topCategory, Pageable page);
	
	Page<Board> findAllByContentContainingAndSubCategoryNotAndTopCategoryEquals(String content, Long subCategory, Byte topCategory, Pageable page);
	
	Page<Board> findAllByContentContainingAndSubCategoryAndTopCategoryEquals(String content, Long subCategory, Byte topCategory, Pageable page);
	
	Page<Board> findAllByWriterContainingAndSubCategoryNotAndTopCategoryEquals(String writer, Long subCategory, Byte topCategory, Pageable page);
	
	Page<Board> findAllByWriterContainingAndSubCategoryAndTopCategoryEquals(String writer, Long subCategory, Byte topCategory, Pageable page);
	// Get count of communities without deleted
	Long countBySubCategoryNotAndTopCategoryEquals(Long subCategory, Byte topCategory);
	// Get count of communities which fit to subCategory
	Long countBySubCategoryAndTopCategoryEquals(Long subCategory, Byte topCategory);
	// Get count of searched communities without deleted
	Long countByTitleContainingAndSubCategoryNotAndTopCategoryEquals(String title, Long subCategory, Byte topCategory);
	// Get count of searched communities which fit to subCategory
	Long countByTitleContainingAndSubCategoryAndTopCategoryEquals(String title, Long subCategory, Byte topCategory);
	
	Long countByContentContainingAndSubCategoryNotAndTopCategoryEquals(String content, Long subCategory, Byte topCategory);
	
	Long countByContentContainingAndSubCategoryAndTopCategoryEquals(String content, Long subCategory, Byte topCategory);
	
	Long countByWriterContainingAndSubCategoryNotAndTopCategoryEquals(String writer, Long subCategory, Byte topCategory);
	
	Long countByWriterContainingAndSubCategoryAndTopCategoryEquals(String writer, Long subCategory, Byte topCategory);
	// Increasing view count when click community
	@Transactional
	@Modifying
	@Query(value="UPDATE board SET view=:view WHERE id=:id", nativeQuery = true)
	void updateViewCount(@Param("view") Long view, @Param("id") Long id);
	// Updating subCategory
	@Transactional
	@Modifying
	@Query(value="UPDATE board SET sub_subCategory, Byte topCategory=:sub_subCategory, Byte topCategory WHERE id=:id", nativeQuery = true)
	void updateCommunitySubCategory(@Param("sub_subCategory, Byte topCategory") Long subCategory, @Param("id") Long id);
}
