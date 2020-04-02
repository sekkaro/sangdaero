package com.sangdaero.walab.user.application.dto;

import java.time.LocalDateTime;

import com.sangdaero.walab.common.entity.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

	private Long id;
	private String name;
	private String nickName;
	private String profile;
	private String socialId;
	private String phone;
	private Integer userType;
	private Integer status;
	private Long volunteerTime;
	private String interest;
	private String service;
	private String memo;
	private Integer locationAgree;
	private Integer phoneAgree;
	private String community;
	private LocalDateTime lastLogin;
	private LocalDateTime regdate;
    private LocalDateTime moddate;
	
	public User toEntity() {
        User build = User.builder()
                .id(id)
                .name(name)
                .nickName(nickName)
                .profile(profile)
                .socialId(socialId)
                .phone(phone)
                .userType(userType)
                .status(status)
                .volunteerTime(volunteerTime)
                .interest(interest)
                .service(service)
                .memo(memo)
                .locationAgree(locationAgree)
                .phoneAgree(phoneAgree)
                .community(community)
                .lastLogin(lastLogin)
                .build();
        
        return build;
    }

	@Builder
	public UserDto(Long id, String name, String nickName, String profile, String socialId, String phone,
			Integer userType, Integer status, Long volunteerTime, String interest, String service, String memo,
			Integer locationAgree, Integer phoneAgree, String community, LocalDateTime lastLogin, LocalDateTime regdate, LocalDateTime moddate) {
		this.id = id;
		this.name = name;
		this.nickName = nickName;
		this.profile = profile;
		this.socialId = socialId;
		this.phone = phone;
		this.userType = userType;
		this.status = status;
		this.volunteerTime = volunteerTime;
		this.interest = interest;
		this.service = service;
		this.memo = memo;
		this.locationAgree = locationAgree;
		this.phoneAgree = phoneAgree;
		this.community = community;
		this.lastLogin = lastLogin;
		this.regdate = regdate;
		this.moddate = moddate;
	}
	
}
