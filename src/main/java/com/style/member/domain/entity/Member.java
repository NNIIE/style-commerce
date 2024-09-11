package com.style.member.domain.entity;

import com.style.brand.domain.entity.Brand;
import com.style.common.domain.entity.BaseEntity;
import com.style.common.exception.brand.BrandException;
import com.style.common.exception.brand.BrandExceptionCode;
import com.style.common.exception.member.MemberException;
import com.style.common.exception.member.MemberExceptionCode;
import com.style.member.domain.MemberRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @UuidGenerator
    @Column(name = "MEMBER_ID", columnDefinition = "BINARY(16)",  updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private MemberRole role;

    @Column(nullable = false)
    private Boolean status;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Brand> brands = new ArrayList<>();

    @Builder
    public Member (
            final String nickname,
            final String email,
            final String password,
            final MemberRole role
    ) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = true;
    }

    public void registerAddress(final Address address) {
        if (addresses.size() >= 2) {
            throw new MemberException(MemberExceptionCode.MAX_ADDRESS);
        }
        this.addresses.add(address);
    }

    public void deleteAddress(final Long addressId) {
        final Address addressToDelete = this.addresses.stream()
                .filter(address -> address.getId().equals(addressId))
                .findFirst()
                .orElseThrow(() -> new MemberException(MemberExceptionCode.ADDRESS_NOT_FOUND));

        this.addresses.remove(addressToDelete);
    }

}
