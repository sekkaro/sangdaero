package com.sangdaero.walab.community.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sangdaero.walab.common.entity.Board;
import com.sangdaero.walab.common.entity.BoardCategory;
import com.sangdaero.walab.community.domain.repository.CategoryRepository;
import com.sangdaero.walab.community.domain.repository.CommunityRepository;
import com.sangdaero.walab.community.dto.CategoryDto;
import com.sangdaero.walab.community.dto.CommunityDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommunityService {

    private CommunityRepository mCommunityRepository;
    private CategoryRepository mCategoryRepository;
    private static final int BLOCK_PAGE_NUMCOUNT = 6; // 블럭에 존재하는 페이지 수
    private static final int PAGE_POSTCOUNT = 3;  // 한 페이지에 존재하는 게시글 수
    private static final Byte topCategory = 2;

    public CommunityService(CommunityRepository communityRepository, CategoryRepository categoryRepository) {
        this.mCommunityRepository = communityRepository;
        this.mCategoryRepository = categoryRepository;
    }
    
    // Convert Community Entity to DTO
    private CommunityDto convertEntityToDto(Board community) {
        return CommunityDto.builder()
                .id(community.getId())
                .title(community.getTitle())
                .content(community.getContent())
                .writer(community.getWriter())
                .view(community.getView())
                .topCategory(community.getTopCategory())
                .categoryId(community.getCategoryId())
                .createdDate(community.getCreatedDate())
                .modifiedDate(community.getModifiedDate())
                .build();
    }

    // Convert Category Entity to DTO
    private CategoryDto convertEntityToDto(BoardCategory category) {
        return CategoryDto.builder()
                .id(category.getId())
                .topCategory(category.getTopCategory())
                .status(category.getStatus())
                .memo(category.getMemo())
                .communityManager(category.getCommunityManager())
                .createdDate(category.getCreatedDate())
                .modifiedDate(category.getModifiedDate())
                .build();
    }
    
    // Save post
    public Long savePost(CommunityDto communityDto) {
        return mCommunityRepository.save(communityDto.toEntity()).getId();
    }
    
    // Update post
    public Long updatePost(CommunityDto communityDto) {
        return mCommunityRepository.save(communityDto.toEntity()).getId();
    }
    
    // Delete post
    public void deletePost(Long id) {
    	Long deleteCategory = (long) 0;
    	mCommunityRepository.updateCommunityCategoryId(deleteCategory, id);
    }

    // getCommunitylist -> convertEntitytoDto
    public List<CommunityDto> getCommunitylist(Integer pageNum, Long categoryId, String keyword, Integer searchType) {
    	Page<Board> page;

   		switch(searchType) {
   			// Search by Title
    		case 1:
    			if (categoryId == 1) {
    				Long deleted = (long) 0;
            		page = mCommunityRepository.findAllByTitleContainingAndCategoryIdNotAndTopCategoryEquals(keyword, deleted, topCategory,
            				PageRequest.of(pageNum-1,PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    			} else {
    				page = mCommunityRepository.findAllByTitleContainingAndCategoryIdAndTopCategoryEquals(keyword, categoryId, topCategory,
            				PageRequest.of(pageNum-1, PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    			}
    			break;
    		// Search by Content
    		case 2:
    			if (categoryId == 1) {
    				Long deleted = (long) 0;
            		page = mCommunityRepository.findAllByContentContainingAndCategoryIdNotAndTopCategoryEquals(keyword, deleted, topCategory,
            				PageRequest.of(pageNum-1,PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    			} else {
    				page = mCommunityRepository.findAllByContentContainingAndCategoryIdAndTopCategoryEquals(keyword, categoryId, topCategory,
            				PageRequest.of(pageNum-1, PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    			}
    			break;
    		// Search by Writer
    		case 3:
    			if (categoryId == 1) {
    				Long deleted = (long) 0;
            		page = mCommunityRepository.findAllByWriterContainingAndCategoryIdNotAndTopCategoryEquals(keyword, deleted, topCategory,
            				PageRequest.of(pageNum-1,PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    			} else {
    				page = mCommunityRepository.findAllByWriterContainingAndCategoryIdAndTopCategoryEquals(keyword, categoryId, topCategory,
            				PageRequest.of(pageNum-1, PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    			}
    			break;
    		// Communities without search
    		default:
    			if (categoryId == 1) {
            		Long deleted = (long) 0;
            		page = mCommunityRepository.findAllByCategoryIdNotAndTopCategoryEquals(deleted, topCategory,
            				PageRequest.of(pageNum-1,PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
            	} else {
            		page = mCommunityRepository.findAllByCategoryIdAndTopCategoryEquals(categoryId, topCategory,
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
    public Integer[] getPageList(Integer curPageNum, Long categoryId, String keyword, Integer searchType) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUMCOUNT];

        // 총 게시글 수
        Double postsTotalCount = Double.valueOf(this.getCommunityCount(categoryId, keyword, searchType));

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
    

    public Long getCommunityCount(Long categoryId, String keyword, Integer searchType) {
    	switch(searchType) {
    		case 1:
    			if (categoryId == 1) {
    				Long deleted = (long) 0;
    	    		return mCommunityRepository.countByTitleContainingAndCategoryIdNotAndTopCategoryEquals(keyword, deleted, topCategory);
    	    	} else {
    	    		return mCommunityRepository.countByTitleContainingAndCategoryIdAndTopCategoryEquals(keyword, categoryId, topCategory);
    	    	}
    		case 2:
    			if (categoryId == 1) {
    				Long deleted = (long) 0;
    	    		return mCommunityRepository.countByContentContainingAndCategoryIdNotAndTopCategoryEquals(keyword, deleted, topCategory);
    	    	} else {
    	    		return mCommunityRepository.countByContentContainingAndCategoryIdAndTopCategoryEquals(keyword, categoryId, topCategory);
    	    	}
    		case 3:
    			if (categoryId == 1) {
    				Long deleted = (long) 0;
    	    		return mCommunityRepository.countByWriterContainingAndCategoryIdNotAndTopCategoryEquals(keyword, deleted, topCategory);
    	    	} else {
    	    		return mCommunityRepository.countByWriterContainingAndCategoryIdAndTopCategoryEquals(keyword, categoryId, topCategory);
    	    	}
    		default:
    			if (categoryId == 1) {
    				Long deleted = (long) 0;
    	    		return mCommunityRepository.countByCategoryIdNotAndTopCategoryEquals(deleted, topCategory);
    	    	} else {
    	    		return mCommunityRepository.countByCategoryIdAndTopCategoryEquals(categoryId, topCategory);
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
                .categoryId(community.getCategoryId())
                .createdDate(community.getCreatedDate())
                .modifiedDate(community.getModifiedDate())
                .build();

        return communityDto;
    }
    
    // Save category
    public Long saveCategory(CategoryDto categoryDto) {
        return mCategoryRepository.save(categoryDto.toEntity()).getId();
    }
    
    // Update category
    public Long updateCategory(CategoryDto categoryDto) {
        return mCategoryRepository.save(categoryDto.toEntity()).getId();
    }
    
    // Delete category
    public void deleteCategory(Long id) {
    	Byte delete = 0;
		mCategoryRepository.updateCommunityCategoryId(delete, id);
    }
    
    public List<CategoryDto> getCategory(Byte topCategory) {
    	List<BoardCategory> boardCategories;
    	
    	if (topCategory != 0) {
    		boardCategories = mCategoryRepository.findAllByTopCategory(topCategory);
    	} else {
    		boardCategories = mCategoryRepository.findAll();
    	}
    	
    	List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
    	
    	for(BoardCategory boardCategory : boardCategories) {
    		categoryDtoList.add(this.convertEntityToDto(boardCategory));
    	}
    	
    	return categoryDtoList;
    	
    }
    
    // Detail of id's category
    public CategoryDto getCategoryDetail(Long id) {
        Optional<BoardCategory> CategoryWrapper = mCategoryRepository.findById(id);
        System.out.println("\n\n"+CategoryWrapper+"\n\n");
        BoardCategory category = CategoryWrapper.get();

        CategoryDto categoryDto = CategoryDto.builder()
                .id(category.getId())
                .topCategory(category.getTopCategory())
                .status(category.getStatus())
                .memo(category.getMemo())
                .communityManager(category.getCommunityManager())
                .createdDate(category.getCreatedDate())
                .modifiedDate(category.getModifiedDate())
                .build();

        return categoryDto;
    }
}
