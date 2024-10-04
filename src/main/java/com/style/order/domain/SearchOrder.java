package com.style.order.domain;

import com.style.member.domain.entity.Address;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class SearchOrder {

    private final Long orderId;
    private final String address;
    private final OrderStatus status;
    private final List<SearchOrderProduct> orderProducts;
    private final BigDecimal totalOrderPrice;

    @Builder
    public SearchOrder(
            final Long orderId,
            final Address address,
            final OrderStatus status,
            final List<SearchOrderProduct> orderProducts
    ) {
        this.orderId = orderId;
        this.address = combineAddress(address);
        this.status = status;
        this.orderProducts = orderProducts;
        this.totalOrderPrice = calculateTotalPrice();
    }

    private String combineAddress(final Address address) {
        return String.format("%s %s %s",
                address.getProvince(),
                address.getCity(),
                address.getDistrict()
        );
    }

    public BigDecimal calculateTotalPrice() {
        return orderProducts.stream()
                .map(product -> product.price().multiply(BigDecimal.valueOf(product.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
