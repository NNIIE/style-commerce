package com.core.entity.member;

import com.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String email;

    private String password;

    private Boolean isAdmin;

    private Boolean status;

    @Builder
    public Member (
            final String nickname,
            final String email,
            final String password,
            final Boolean isAdmin
    ) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.status = true;
    }

}
