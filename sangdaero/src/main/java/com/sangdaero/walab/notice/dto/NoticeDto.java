package com.sangdaero.walab.notice.dto;



import java.time.LocalDateTime;

import com.sangdaero.walab.common.board.domain.entity.Board;
import com.sangdaero.walab.notice.domain.entity.Notice;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoticeDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private Long view;
    private Long scope;
    private Byte top_category;
    private Long sub_category;  
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Notice toEntity() {
        Notice notice = Notice.builder()
                .id(id)
                .title(title)
                .content(content)
                .writer(writer)
                .scope(scope)
                .top_category(top_category)
                .sub_category(sub_category)
                .build();
        return notice;
    }

    @Builder
    public NoticeDto(Long id, String title, String content, String writer, Long view, Long scope, Byte top_category, Long sub_category, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.view = view;
        this.scope = scope;
        this.top_category = top_category;
        this.sub_category = sub_category;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
