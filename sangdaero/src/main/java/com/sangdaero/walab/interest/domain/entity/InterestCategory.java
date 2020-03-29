package com.sangdaero.walab.interest.domain.entity;

import com.sangdaero.walab.common.board.domain.entity.TimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    @Builder
    public InterestCategory(Long id, String name, Byte type, Byte on_off) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.on_off = 1;
    }
}
