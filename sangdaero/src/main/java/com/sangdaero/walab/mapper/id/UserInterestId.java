package com.sangdaero.walab.mapper.id;

import java.io.Serializable;

public class UserInterestId implements Serializable {
    private Long user;
    private Long interestCategory;

    public UserInterestId() {
    }

    public UserInterestId(Long user, Long interestCategory) {
        this.user = user;
        this.interestCategory = interestCategory;
    }
}
