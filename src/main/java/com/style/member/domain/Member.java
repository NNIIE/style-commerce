package com.style.member.domain;

import com.style.common.domain.entity.BaseEntity;
import com.style.common.exception.member.MemberException;
import com.style.common.exception.member.MemberExceptionCode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @UuidGenerator
    @Column(name = "MEMBER_ID", columnDefinition = "BINARY(16)",  updatable = false, nullable = false)
    private UUID id;

    private String nickname;

    private String email;

    private String password;

    private Boolean isAdmin;

    private Boolean status;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

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

    public void addAddress(final Address address) {
        if (addresses.size() >= 2) {
            throw new MemberException(MemberExceptionCode.MAX_ADDRESS);
        }
        this.addresses.add(address);
    }

    public void deleteAddress(final Long addressId) {
        Address addressToDelete = this.addresses.stream()
                .filter(address -> address.getId().equals(addressId))
                .findFirst()
                .orElseThrow(() -> new MemberException(MemberExceptionCode.ADDRESS_NOT_FOUND));

        addressToDelete.setMember(null);
        this.addresses.remove(addressToDelete);
    }

}
