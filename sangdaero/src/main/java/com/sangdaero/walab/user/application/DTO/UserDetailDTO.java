package com.sangdaero.walab.user.application.DTO;

import com.sangdaero.walab.interest.domain.entity.InterestCategory;
import com.sangdaero.walab.user.domain.entity.User;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDetailDTO {
    private Long id;
    private String name;
    private String nickname;
    private String socialId;
    private String phone;
    private Byte userType;
    private Integer volunteerTime;
    private Set<String> interestName = new HashSet<>();

    public User toEntity() {
        User user = User.builder()
                .id(id)
                .name(name)
                .nickname(nickname)
                .socialId(socialId)
                .phone(phone)
                .userType(userType)
                .volunteerTime(volunteerTime)
                .build();
        return user;
    }

    @Builder
    public UserDetailDTO(Long id, String name, String nickname, String socialId, String phone,
                         Byte userType, Integer volunteerTime, Set<String> interestName) {
        this.id=id;
        this.name=name;
        this.nickname=nickname;
        this.socialId=socialId;
        this.phone=phone;
        this.userType=userType;
        this.volunteerTime=volunteerTime;
        this.interestName=interestName;
    }

}
