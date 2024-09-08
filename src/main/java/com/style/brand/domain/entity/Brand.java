package com.style.brand.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.style.common.domain.entity.BaseEntity;
import com.style.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Brand extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    @JsonIgnore
    private Member owner;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long licenseNumber;

    @Column(nullable = false)
    private Long phoneNumber;

    @Builder
    public Brand(
            final Member owner,
            final String name,
            final Long licenseNumber,
            final Long phoneNumber
    ) {
        this.owner = owner;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.phoneNumber = phoneNumber;
    }

}
