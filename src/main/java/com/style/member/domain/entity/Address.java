package com.style.member.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.style.common.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    @JsonIgnore
    private Member member;

    @Column(nullable = false)
    private String province;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String district;

    @Builder
    public Address(
            final Member member,
            final String province,
            final String city,
            final String district
    ) {
        this.member = member;
        this.province = province;
        this.city = city;
        this.district = district;
    }

}
