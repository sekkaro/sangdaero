package com.sangdaero.walab.interest.domain.entity;

import com.sangdaero.walab.common.board.domain.entity.TimeEntity;
import com.sangdaero.walab.mapper.domain.UserInterest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterestCategory extends TimeEntity {

    @Id @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    private Byte type;

    private Byte on_off;

    @OneToMany(mappedBy = "interestCategory")
    private List<UserInterest> userInterestList = new ArrayList<>();

    @Builder
    public InterestCategory(Long id, String name, Byte type, Byte on_off, List<UserInterest> userInterestList) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.on_off = 1;
        this.userInterestList = userInterestList;
    }
}
