package com.style.order.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.style.common.domain.entity.BaseEntity;
import com.style.member.domain.entity.Address;
import com.style.member.domain.entity.Member;
import com.style.order.domain.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    @JsonIgnore
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(nullable = false)
    private OrderStatus status;

    @Builder
    public Order(final Member member, final OrderStatus status, final Address address) {
        this.member = member;
        this.status = status;
        this.address = address;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

}
