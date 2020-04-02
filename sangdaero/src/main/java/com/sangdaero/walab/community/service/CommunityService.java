package com.sangdaero.walab.community.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sangdaero.walab.common.entity.Board;
import com.sangdaero.walab.community.domain.repository.CommunityRepository;
import com.sangdaero.walab.community.dto.CommunityDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommunityService {

    private CommunityRepository mCommunityRepository;
    private static final int BLOCK_PAGE_NUMCOUNT = 6; // 블럭에 존재하는 페이지 수
    private static final int PAGE_POSTCOUNT = 3;  // 한 페이지에 존재하는 게시글 수
    private static final Byte topCategory = 2;

    public CommunityService(CommunityRepository communityRepository) {
        this.mCommunityRepository = communityRepository;
    }
    
    // Convert Entity to DTO
    private CommunityDto convertEntityToDto(Board community) {
        return CommunityDto.builder()
                .id(community.getId())
                .title(community.getTitle())
                .content(community.getContent())
                .writer(community.getWriter())
                .view(community.getView())
                .topCategory(community.getTopCategory())
                .subCategory(community.getSubCategory())
                .createdDate(community.getCreatedDate())
                .modifiedDate(community.getModifiedDate())
                .build();
    }

    // Save
    public Long savePost(CommunityDto communityDto) {
        return mCommunityRepository.save(communityDto.toEntity()).getId();
    }
    
    // Update
    public Long updatePost(CommunityDto communityDto) {
        return mCommunityRepository.save(communityDto.toEntity()).getId();
    }
    
    // Delete
    public void deletePost(Long id) {
    	Long deleteCategory = (long) 0;
    	mCommunityRepository.updateCommunitySubCategory(deleteCategory, id);
    }

    // getCommunitylist -> convertEntitytoDto
    public List<CommunityDto> getCommunitylist(Integer pageNum, Long subCategory, String keyword, Integer searchType) {
    	Page<Board> page;

   		switch(searchType) {
   			// Search by Title
    		case 1:
    			if (subCategory == 1) {
    				Long deleted = (long) 0;
            		page = mCommunityRepository.findAllByTitleContainingAndSubCategoryNotAndTopCategoryEquals(keyword, deleted, topCategory,
            				PageRequest.of(pageNum-1,PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    			} else {
    				page = mCommunityRepository.findAllByTitleContainingAndSubCategoryAndTopCategoryEquals(keyword, subCategory, topCategory,
            				PageRequest.of(pageNum-1, PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    			}
    			break;
    		// Search by Content
    		case 2:
    			if (subCategory == 1) {
    				Long deleted = (long) 0;
            		page = mCommunityRepository.findAllByContentContainingAndSubCategoryNotAndTopCategoryEquals(keyword, deleted, topCategory,
            				PageRequest.of(pageNum-1,PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    			} else {
    				page = mCommunityRepository.findAllByContentContainingAndSubCategoryAndTopCategoryEquals(keyword, subCategory, topCategory,
            				PageRequest.of(pageNum-1, PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    			}
    			break;
    		// Search by Writer
    		case 3:
    			if (subCategory == 1) {
    				Long deleted = (long) 0;
            		page = mCommunityRepository.findAllByWriterContainingAndSubCategoryNotAndTopCategoryEquals(keyword, deleted, topCategory,
            				PageRequest.of(pageNum-1,PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    			} else {
    				page = mCommunityRepository.findAllByWriterContainingAndSubCategoryAndTopCategoryEquals(keyword, subCategory, topCategory,
            				PageRequest.of(pageNum-1, PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    			}
    			break;
    		// Communitys without search
    		default:
    			if (subCategory == 1) {
            		Long deleted = (long) 0;
            		page = mCommunityRepository.findAllBySubCategoryNotAndTopCategoryEquals(deleted, topCategory,
            				PageRequest.of(pageNum-1,PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
            	} else {
            		page = mCommunityRepository.findAllBySubCategoryAndTopCategoryEquals(subCategory, topCategory,
            				PageRequest.of(pageNum-1, PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
            	}
    			break;
    	}
    	
        List<Board> communities = page.getContent();
        List<CommunityDto> communityDtoList = new ArrayList<>();

        for(Board community : communities) {
        	communityDtoList.add(this.convertEntityToDto(community));
        }
        
        return communityDtoList;
    }
    
    
    // getPageList -> getCommunityCount
    public Integer[] getPageList(Integer curPageNum, Long subCategory, String keyword, Integer searchType) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUMCOUNT];

        // 총 게시글 수
        Double postsTotalCount = Double.valueOf(this.getCommunityCount(subCategory, keyword, searchType));

        // 총 게시글 수를 기준으로 계산한 마지막 페이지 번호 계산
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POSTCOUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUMCOUNT)
                ? curPageNum + BLOCK_PAGE_NUMCOUNT
                : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum<=3) ? 1 : curPageNum-2;

        // 페이지 번호 할당
        for(int val=curPageNum, i=0;val<=blockLastPageNum;val++, i++) {
            pageList[i] = val;
        }

        return pageList;
    }
    

    public Long getCommunityCount(Long subCategory, String keyword, Integer searchType) {
    	switch(searchType) {
    		case 1:
    			if (subCategory == 1) {
    				Long deleted = (long) 0;
    	    		return mCommunityRepository.countByTitleContainingAndSubCategoryNotAndTopCategoryEquals(keyword, deleted, topCategory);
    	    	} else {
    	    		return mCommunityRepository.countByTitleContainingAndSubCategoryAndTopCategoryEquals(keyword, subCategory, topCategory);
    	    	}
    		case 2:
    			if (subCategory == 1) {
    				Long deleted = (long) 0;
    	    		return mCommunityRepository.countByContentContainingAndSubCategoryNotAndTopCategoryEquals(keyword, deleted, topCategory);
    	    	} else {
    	    		return mCommunityRepository.countByContentContainingAndSubCategoryAndTopCategoryEquals(keyword, subCategory, topCategory);
    	    	}
    		case 3:
    			if (subCategory == 1) {
    				Long deleted = (long) 0;
    	    		return mCommunityRepository.countByWriterContainingAndSubCategoryNotAndTopCategoryEquals(keyword, deleted, topCategory);
    	    	} else {
    	    		return mCommunityRepository.countByWriterContainingAndSubCategoryAndTopCategoryEquals(keyword, subCategory, topCategory);
    	    	}
    		default:
    			if (subCategory == 1) {
    				Long deleted = (long) 0;
    	    		return mCommunityRepository.countBySubCategoryNotAndTopCategoryEquals(deleted, topCategory);
    	    	} else {
    	    		return mCommunityRepository.countBySubCategoryAndTopCategoryEquals(subCategory, topCategory);
    	    	}
    	}
    }
    
    // Detail of id's community
    public CommunityDto getPost(Long id) {
        Optional<Board> CommunityWrapper = mCommunityRepository.findById(id);
        Board community = CommunityWrapper.get();
        
        mCommunityRepository.updateViewCount(community.getView() + 1, id);

        CommunityDto communityDto = CommunityDto.builder()
                .id(community.getId())
                .title(community.getTitle())
                .content(community.getContent())
                .writer(community.getWriter())
                .view(community.getView()+ 1)
                .topCategory(community.getTopCategory())
                .subCategory(community.getSubCategory())
                .createdDate(community.getCreatedDate())
                .modifiedDate(community.getModifiedDate())
                .build();

        return communityDto;
    }
    
	/*
	 * // Search post public List<CommunityDto> searchPosts(String keyword, int
	 * searchType) { Page<Community> page;
	 * 
	 * switch(searchType) { case 1: List<Community> communitys =
	 * mCommunityRepository.findByTitleContaining(keyword); break; } List<Community>
	 * communitys = mCommunityRepository.findByTitleContaining(keyword); List<CommunityDto>
	 * communityDtoList = new ArrayList<>();
	 * 
	 * if(communitys.isEmpty()) return communityDtoList;
	 * 
	 * for(Community Community : communitys) {
	 * communityDtoList.add(this.convertEntityToDto(Community)); }
	 * 
	 * return communityDtoList; }
	 */
}
