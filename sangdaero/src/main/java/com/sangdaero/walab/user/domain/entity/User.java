package com.sangdaero.walab.user.domain.entity;

import com.sangdaero.walab.common.board.domain.entity.TimeEntity;

import com.sangdaero.walab.mapper.domain.UserInterest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends TimeEntity {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String nickname;

    private String profile;

    private String socialId;

    private String phone;

    private Byte userType;

    private Byte status;

    private Integer volunteerTime;

    @OneToMany(mappedBy = "user")
    private List<UserInterest> userInterestList = new ArrayList<>();

    private String service;

    private String memo;

    private Byte locationAgree;

    private Byte phoneAgree;

    private String community;

    private LocalDateTime lastLogin;

    private Byte isDelete;

    @Builder
    public User(Long id, String name, String nickname, String profile, String socialId,
                String phone, Byte userType, Byte status, Integer volunteerTime, List<UserInterest> userInterestList,
                String service, String memo, Byte locationAgree, Byte phoneAgree, String community,
                LocalDateTime lastLogin, Byte isDelete) {

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
    }

}
