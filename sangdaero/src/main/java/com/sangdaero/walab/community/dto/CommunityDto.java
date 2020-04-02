package com.sangdaero.walab.community.dto;



import java.time.LocalDateTime;

import com.sangdaero.walab.common.board.domain.entity.CommonBoard;
import com.sangdaero.walab.common.entity.Board;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommunityDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private Long view;
    private Byte topCategory;
    private Long subCategory;  
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Board toEntity() {
        Board board = Board.builder()
                .id(id)
                .title(title)
                .content(content)
                .writer(writer)
                .view(view)
                .topCategory(topCategory)
                .subCategory(subCategory)
                .build();
        return board;
    }

    @Builder
    public CommunityDto(Long id, String title, String content, String writer, Long view, Byte topCategory, Long subCategory, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.view = view;
        this.topCategory = topCategory;
        this.subCategory = subCategory;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
