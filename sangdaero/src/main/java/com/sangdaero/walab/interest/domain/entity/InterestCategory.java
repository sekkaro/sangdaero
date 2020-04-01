package com.sangdaero.walab.interest.domain.entity;

import com.sangdaero.walab.common.board.domain.entity.TimeEntity;
import com.sangdaero.walab.user.domain.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class InterestCategory extends TimeEntity {

    @Id @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    private Byte type;

    private Byte on_off;

//    @OneToMany(mappedBy = "interest")
//    private Set<UserInterest> users = new HashSet<>();

    @Builder
    public InterestCategory(Long id, String name, Byte type, Byte on_off) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.on_off = 1;
    }
}
