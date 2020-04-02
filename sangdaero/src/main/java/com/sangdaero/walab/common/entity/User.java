package com.sangdaero.walab.common.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.sangdaero.walab.common.board.domain.entity.TimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends TimeEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length=255, nullable = false)
	private String name;
	
	@Column(length=255, nullable = false)
	private String nickName;
	
	@Column(length=255, nullable = false)
	private String profile;
	
	@Column(length=255, nullable = false)
	private String socialId;
	
	@Column(length=255, nullable = false)
	private String phone;
	
	@Column(columnDefinition="tinyint", nullable = false)
	private Integer userType;
	
	@Column(columnDefinition="tinyint", nullable = false)
	private Integer status;
	
	@Column(nullable = false)
	private Long volunteerTime;
	
	@Column(length=255)
	private String interest;
	
	@Column(length=255)
	private String service;
	
	@Column(columnDefinition="TEXT")
	private String memo;
	
	@Column(columnDefinition="tinyint", nullable = false)
	private Integer locationAgree;
	
	@Column(columnDefinition="tinyint", nullable = false)
	private Integer phoneAgree;
	
	@Column(length=255)
	private String community;
	
	@Column
	private LocalDateTime lastLogin;
	
	
	@Builder
	public User(Long id, String name, String nickName, String profile, String socialId, String phone, Integer userType,
			Integer status, Long volunteerTime, String interest, String service, String memo, Integer locationAgree,
			Integer phoneAgree, String community, LocalDateTime lastLogin) {
		this.id = id;
		this.name = name;
		this.nickName = (nickName != null)?nickName:"nickname";
		this.profile = profile;
		this.socialId = socialId;
		this.phone = (phone != null)?phone:"010-XXXX-XXXX";
		this.userType = 0;
		this.status = 1;
		this.volunteerTime = (long) 0;
		this.interest = interest;
		this.service = service;
		this.memo = memo;
		this.locationAgree = 0;
		this.phoneAgree = 0;
		this.community = community;
		this.lastLogin = lastLogin;
	}
	
	
}
