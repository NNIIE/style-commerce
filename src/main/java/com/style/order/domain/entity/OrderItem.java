package com.style.order.domain.entity;

import com.style.common.domain.entity.BaseEntity;
import com.style.product.domain.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private Integer quantity;

    @Builder
    public OrderItem(final Order order, final Product product, final Integer quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

}
