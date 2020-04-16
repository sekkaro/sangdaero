package com.sangdaero.walab.common.entity;

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

public class File extends TimeEntity {
	
	@Id
    @GeneratedValue
    private Long id;
	
	@Column(name="file_name", length = 255, nullable = false)
	private String fileName;
	
	@Column(name="event_id")
	private Long eventId;
	
	@Column(name="board_id")
	private Long boardId;
	
	@Column(length = 255, nullable = false)
	private String url;
	
	@Builder
	public File(Long id, String fileName, Long eventId, Long boardId, String url) {
		this.id = id;
		this.fileName = fileName;
		this.eventId = eventId;
		this.boardId = boardId;
		this.url = url;
	}
}
