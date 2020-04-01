package com.sangdaero.walab.user.application.DTO;

import com.sangdaero.walab.interest.domain.entity.InterestCategory;

import com.sangdaero.walab.user.domain.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String nickname;
    private String profile;
    private String socialId;
    private String phone;
    private Byte userType;
    private Byte status;
    private Integer volunteerTime;
    private String[] userInterestList;
//    private Set<UserInterest> interests = new HashSet<>();
    private Set<InterestCategory> interests = new HashSet<>();
    private String service;
    private String memo;
    private Byte locationAgree;
    private Byte phoneAgree;
    private String community;
    private LocalDateTime lastLogin;
    private Byte isDelete;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public User toEntity() {
        User user = User.builder()
                .id(id)
                .name(name)
                .nickname(nickname)
                .profile(profile)
                .socialId(socialId)
                .phone(phone)
                .userType(userType)
                .status(status)
                .volunteerTime(volunteerTime)
                .interests(interests)
                .service(service)
                .memo(memo)
                .locationAgree(locationAgree)
                .phoneAgree(phoneAgree)
                .community(community)
                .lastLogin(lastLogin)
                .isDelete(isDelete)
                .build();
        return user;
    }

    @Builder
    public UserDTO(Long id, String name, String nickname, String profile, String socialId,
                   String phone, Byte userType, Byte status, Integer volunteerTime,
                   String[] userInterestList, String service, String memo,
                   Byte locationAgree, Byte phoneAgree, String community, LocalDateTime lastLogin,
                   Byte isDelete, LocalDateTime createdDate, LocalDateTime modifiedDate) {

        this.id=id;
        this.name=name;
        this.nickname=nickname;
        this.profile=profile;
        this.socialId=socialId;
        this.phone=phone;
        this.userType=userType;
        this.status=status;
        this.volunteerTime=volunteerTime;
        this.userInterestList=userInterestList;
        this.service=service;
        this.memo=memo;
        this.locationAgree=locationAgree;
        this.phoneAgree=phoneAgree;
        this.community=community;
        this.lastLogin=lastLogin;
        this.isDelete=isDelete;
        this.createdDate=createdDate;
        this.modifiedDate=modifiedDate;
    }

}
