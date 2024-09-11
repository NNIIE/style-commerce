package com.style.brand.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.style.brand.presentation.request.UpdateBrandRequest;
import com.style.common.domain.entity.BaseEntity;
import com.style.member.domain.entity.Member;
import com.style.product.domain.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products = new HashSet<>();

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

    public void registerProduct(final Product product) {
        this.products.add((product));
    }

    public void update(final UpdateBrandRequest request) {
        if (request.isNameUpdate()) {
            this.setName(request.getName());
        }

        if (request.isPhoneNumberUpdate()) {
            this.setPhoneNumber(request.getPhoneNumber());
        }
    }

}
