package com.style.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.style.common.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    @JsonIgnore
    private Member member;

    private String province;

    private String city;

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
