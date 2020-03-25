package com.sangdaero.walab.notice.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Notice extends TimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    
    @Column(length = 10, nullable = false)
    private String writer;
    
    @Column
    @ColumnDefault("0")
    private Long view;
    
    @Column(nullable = false)
    private Long scope;
    
    @Column(columnDefinition="TINYINT", length = 1)
    @ColumnDefault("1")
    private Byte status;
    
    @Column(columnDefinition="TINYINT", length = 1, nullable = false)
    private Byte top_category;
    
    @Column(nullable = false)
    private Long sub_category;
    
    @Column(columnDefinition="TINYINT", length = 1)
    @ColumnDefault("0")
    private Byte qna; 

    @Builder
    public Notice(Long id, String title, String content, String writer, Long scope, Byte top_category, Long sub_category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.scope = scope;
        this.top_category = top_category;
        this.sub_category = sub_category;
    }

}
