package com.sangdaero.walab.notice.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sangdaero.walab.common.entity.Board;
import com.sangdaero.walab.notice.domain.repository.NoticeRepository;
import com.sangdaero.walab.notice.dto.NoticeDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {

    private NoticeRepository mNoticeRepository;
    private static final int BLOCK_PAGE_NUMCOUNT = 6; // 블럭에 존재하는 페이지 수
    private static final int PAGE_POSTCOUNT = 3;  // 한 페이지에 존재하는 게시글 수
    private static final Byte topCategory = 1;

    public NoticeService(NoticeRepository noticeRepository) {
        this.mNoticeRepository = noticeRepository;
    }
    
    // Convert Entity to DTO
    private NoticeDto convertEntityToDto(Board notice) {
        return NoticeDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .writer(notice.getWriter())
                .view(notice.getView())
                .topCategory(notice.getTopCategory())
                .categoryId(notice.getCategoryId())
                .createdDate(notice.getCreatedDate())
                .modifiedDate(notice.getModifiedDate())
                .build();
    }

    // Save
    public Long savePost(NoticeDto noticeDto) {
        return mNoticeRepository.save(noticeDto.toEntity()).getId();
    }
    
    // Update
    public Long updatePost(NoticeDto noticeDto) {
        return mNoticeRepository.save(noticeDto.toEntity()).getId();
    }
    
    // Delete
    public void deletePost(Long id) {
    	Long deleteCategory = (long) 0;
    	mNoticeRepository.updateNoticeCategoryId(deleteCategory, id);
    }

    // getNoticelist -> convertEntitytoDto
    public List<NoticeDto> getNoticelist(Integer pageNum, Long categoryId, String keyword, Integer searchType) {
    	Page<Board> page;

   		switch(searchType) {
   			// Search by Title
    		case 1:
    			if (categoryId == 1) {
    				Long deleted = (long) 0;
            		page = mNoticeRepository.findAllByTitleContainingAndCategoryIdNotAndTopCategoryEquals(keyword, deleted, topCategory,
            				PageRequest.of(pageNum-1,PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    			} else {
    				page = mNoticeRepository.findAllByTitleContainingAndCategoryIdAndTopCategoryEquals(keyword, categoryId, topCategory,
            				PageRequest.of(pageNum-1, PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    			}
    			break;
    		// Search by Content
    		case 2:
    			if (categoryId == 1) {
    				Long deleted = (long) 0;
            		page = mNoticeRepository.findAllByContentContainingAndCategoryIdNotAndTopCategoryEquals(keyword, deleted, topCategory,
            				PageRequest.of(pageNum-1,PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    			} else {
    				page = mNoticeRepository.findAllByContentContainingAndCategoryIdAndTopCategoryEquals(keyword, categoryId, topCategory,
            				PageRequest.of(pageNum-1, PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    			}
    			break;
    		// Search by Writer
    		case 3:
    			if (categoryId == 1) {
    				Long deleted = (long) 0;
            		page = mNoticeRepository.findAllByWriterContainingAndCategoryIdNotAndTopCategoryEquals(keyword, deleted, topCategory,
            				PageRequest.of(pageNum-1,PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    			} else {
    				page = mNoticeRepository.findAllByWriterContainingAndCategoryIdAndTopCategoryEquals(keyword, categoryId, topCategory,
            				PageRequest.of(pageNum-1, PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    			}
    			break;
    		// Notices without search
    		default:
    			if (categoryId == 1) {
            		Long deleted = (long) 0;
            		
            		System.out.println("\n\n");
    				System.out.println(deleted);
    				System.out.println(topCategory);
    				System.out.println("\n\n");
            		page = mNoticeRepository.findAllByCategoryIdNotAndTopCategoryEquals(deleted, topCategory,
            				PageRequest.of(pageNum-1,PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
            	} else {
            		page = mNoticeRepository.findAllByCategoryIdAndTopCategoryEquals(categoryId, topCategory,
            				PageRequest.of(pageNum-1, PAGE_POSTCOUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
            	}
    			break;
    	}
    	
        List<Board> notices = page.getContent();
        List<NoticeDto> noticeDtoList = new ArrayList<>();

        for(Board notice : notices) {
        	noticeDtoList.add(this.convertEntityToDto(notice));
        }
        
        return noticeDtoList;
    }
    
    
    // getPageList -> getNoticeCount
    public Integer[] getPageList(Integer curPageNum, Long categoryId, String keyword, Integer searchType) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUMCOUNT];

        // 총 게시글 수
        Double postsTotalCount = Double.valueOf(this.getNoticeCount(categoryId, keyword, searchType));

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
    

    public Long getNoticeCount(Long categoryId, String keyword, Integer searchType) {
    	
    	switch(searchType) {
    		case 1:
    			if (categoryId == 1) {
    				Long deleted = (long) 0;
    	    		return mNoticeRepository.countByTitleContainingAndCategoryIdNotAndTopCategoryEquals(keyword, deleted, topCategory);
    	    	} else {
    	    		return mNoticeRepository.countByTitleContainingAndCategoryIdAndTopCategoryEquals(keyword, categoryId, topCategory);
    	    	}
    		case 2:
    			if (categoryId == 1) {
    				Long deleted = (long) 0;
    	    		return mNoticeRepository.countByContentContainingAndCategoryIdNotAndTopCategoryEquals(keyword, deleted, topCategory);
    	    	} else {
    	    		return mNoticeRepository.countByContentContainingAndCategoryIdAndTopCategoryEquals(keyword, categoryId, topCategory);
    	    	}
    		case 3:
    			if (categoryId == 1) {
    				Long deleted = (long) 0;
    	    		return mNoticeRepository.countByWriterContainingAndCategoryIdNotAndTopCategoryEquals(keyword, deleted, topCategory);
    	    	} else {
    	    		return mNoticeRepository.countByWriterContainingAndCategoryIdAndTopCategoryEquals(keyword, categoryId, topCategory);
    	    	}
    		default:
    			if (categoryId == 1) {
    				Long deleted = (long) 0;
    				
    				System.out.println("\n\n");
    				System.out.println(deleted);
    				System.out.println(topCategory);
    				System.out.println("\n\n");
    				
    	    		return mNoticeRepository.countByCategoryIdNotAndTopCategoryEquals(deleted, topCategory);
    	    	} else {
    	    		return mNoticeRepository.countByCategoryIdAndTopCategoryEquals(categoryId, topCategory);
    	    	}
    	}
    }
    
    // Detail of id's notice
    public NoticeDto getPost(Long id) {
        Optional<Board> NoticeWrapper = mNoticeRepository.findById(id);
        Board notice = NoticeWrapper.get();
        
        mNoticeRepository.updateViewCount(notice.getView() + 1, id);

        NoticeDto noticeDto = NoticeDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .writer(notice.getWriter())
                .view(notice.getView()+ 1)
                .topCategory(notice.getTopCategory())
                .categoryId(notice.getCategoryId())
                .createdDate(notice.getCreatedDate())
                .modifiedDate(notice.getModifiedDate())
                .build();

        return noticeDto;
    }
    
	/*
	 * // Search post public List<NoticeDto> searchPosts(String keyword, int
	 * searchType) { Page<Notice> page;
	 * 
	 * switch(searchType) { case 1: List<Notice> notices =
	 * mNoticeRepository.findByTitleContaining(keyword); break; } List<Notice>
	 * notices = mNoticeRepository.findByTitleContaining(keyword); List<NoticeDto>
	 * noticeDtoList = new ArrayList<>();
	 * 
	 * if(notices.isEmpty()) return noticeDtoList;
	 * 
	 * for(Notice Notice : notices) {
	 * noticeDtoList.add(this.convertEntityToDto(Notice)); }
	 * 
	 * return noticeDtoList; }
	 */
}
