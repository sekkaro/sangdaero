package com.sangdaero.walab.notice.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sangdaero.walab.notice.dto.NoticeDto;
import com.sangdaero.walab.notice.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	private NoticeService mNoticeService;
	
	public NoticeController(NoticeService noticeService) {
		this.mNoticeService = noticeService;
	}
	
	@GetMapping("")
	public String noticePage() {
		return "html/notice/list.html";
	}

	@GetMapping("/post")
    public String write() {
        return "notice/write.html";
    }

    @PostMapping("/post")
    public String write(NoticeDto noticeDto) {
        mNoticeService.savePost(noticeDto);
        return "redirect:/notice";
    }

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long id, Model model) {
        NoticeDto noticeDto = mNoticeService.getPost(id);

        model.addAttribute("noticeDto", noticeDto);
        return "notice/detail.html";
    }

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long id, Model model) {
        NoticeDto noticeDto = mNoticeService.getPost(id);

        model.addAttribute("noticeDto", noticeDto);
        return "notice/update.html";
    }

    @PutMapping("/post/edit/{no}")
    public String update(NoticeDto noticeDto) {
        mNoticeService.savePost(noticeDto);
        return "redirect:/notice";
    }

    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long id) {
        mNoticeService.deletePost(id);

        return "redirect:/notice";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<NoticeDto> noticeDtoList = mNoticeService.searchPosts(keyword);
        model.addAttribute("noticeList", noticeDtoList);

        return "notice/list.html";
    }
	
}
