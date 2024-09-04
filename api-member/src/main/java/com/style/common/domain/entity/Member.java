package com.style.common.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @UuidGenerator
    @Column(columnDefinition = "BINARY(16)",  updatable = false, nullable = false)
    private UUID id;

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
