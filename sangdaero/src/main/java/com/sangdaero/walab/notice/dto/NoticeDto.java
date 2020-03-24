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
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Notice toEntity() {
        Notice notice = Notice.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .build();
        return notice;
    }

    @Builder
    public NoticeDto(Long id, String title, String content, String writer, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
