package com.sangdaero.walab.notice.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sangdaero.walab.notice.domain.entity.Notice;
import com.sangdaero.walab.notice.domain.repository.NoticeRepository;
import com.sangdaero.walab.notice.dto.NoticeDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {

    private NoticeRepository mNoticeRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 6; // 블럭에 존재하는 페이지 수
    private static final int PAGE_POST_COUNT = 3;  // 한 페이지에 존재하는 게시글 수

    public NoticeService(NoticeRepository noticeRepository) {
        this.mNoticeRepository = noticeRepository;
    }

    public Long savePost(NoticeDto noticeDto) {
        return mNoticeRepository.save(noticeDto.toEntity()).getId();
    }

    public List<NoticeDto> getNoticelist(Integer pageNum) {

        Page<Notice> page = mNoticeRepository
                .findAll(PageRequest
                        .of(pageNum-1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "createdDate")));

        //List<Notice> Notices = NoticeRepository.findAll();
        List<Notice> notices = page.getContent();
        List<NoticeDto> noticeDtoList = new ArrayList<>();

        for(Notice Notice : notices) {
            noticeDtoList.add(this.convertEntityToDto(Notice));
        }
        
        System.out.println("via noticelist");

        return noticeDtoList;
    }

    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 게시글 수
        Double postsTotalCount = Double.valueOf(this.getNoticeCount());

        // 총 게시글 수를 기준으로 계산한 마지막 페이지 번호 계산
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum<=3) ? 1 : curPageNum-2;

        // 페이지 번호 할당
        for(int val=curPageNum, i=0;val<=blockLastPageNum;val++, i++) {
            pageList[i] = val;
        }
        
        System.out.println("via pagelist");

        return pageList;
    }

    public Long getNoticeCount() {
        return mNoticeRepository.count();
    }

    public NoticeDto getPost(Long id) {
        Optional<Notice> NoticeWrapper = mNoticeRepository.findById(id);
        Notice notice = NoticeWrapper.get();

        NoticeDto noticeDto = NoticeDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .writer(notice.getWriter())
                .view(notice.getView())
                .scope(notice.getScope())
                .top_category(notice.getTop_category())
                .sub_category(notice.getSub_category())
                .createdDate(notice.getCreatedDate())
                .build();

        return noticeDto;
    }

    public void deletePost(Long id) {
        mNoticeRepository.deleteById(id);
    }

    public List<NoticeDto> searchPosts(String keyword) {
        List<Notice> notices = mNoticeRepository.findByTitleContaining(keyword);
        List<NoticeDto> noticeDtoList = new ArrayList<>();

        if(notices.isEmpty()) return noticeDtoList;

        for(Notice Notice : notices) {
            noticeDtoList.add(this.convertEntityToDto(Notice));
        }

        return noticeDtoList;
    }

    private NoticeDto convertEntityToDto(Notice notice) {
        return NoticeDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .writer(notice.getWriter())
                .view(notice.getView())
                .scope(notice.getScope())
                .top_category(notice.getTop_category())
                .sub_category(notice.getSub_category())
                .createdDate(notice.getCreatedDate())
                .build();
    }
}
