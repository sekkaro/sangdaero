package com.sangdaero.walab.user.domain.entity;

import com.sangdaero.walab.common.board.domain.entity.TimeEntity;

import com.sangdaero.walab.interest.domain.entity.InterestCategory;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @OneToMany
    private Set<InterestCategory> interests = new HashSet<>();

    private String service;

    private String memo;

    private Byte locationAgree;

    private Byte phoneAgree;

    private String community;

    private LocalDateTime lastLogin;

    private Byte isDelete;

    @Builder
    public User(Long id, String name, String nickname, String profile, String socialId,
                String phone, Byte userType, Byte status, Integer volunteerTime, Set<InterestCategory> interests,
                String service, String memo, Byte locationAgree, Byte phoneAgree, String community,
                LocalDateTime lastLogin, Byte isDelete) {

        this.id=id;
        this.name=name;
        this.nickname=nickname;
        this.profile=profile;
        this.socialId="21500153@handong.edu";
        this.phone=phone;
        this.userType=2;
        this.status=1;
        this.volunteerTime=0;
        this.interests=interests;
        this.service=service;
        this.memo=memo;
        this.locationAgree=0;
        this.phoneAgree=0;
        this.community=community;
        this.lastLogin=lastLogin;
        this.isDelete=0;
    }
}
