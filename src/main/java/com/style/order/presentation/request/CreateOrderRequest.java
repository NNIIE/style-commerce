package com.style.order.presentation.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class CreateOrderRequest {

    @NotNull(message = "주문 상품이 없습니다.")
    private List<OrderProduct> orderProducts;

    public List<Long> getOrderProductIds() {
        return orderProducts.stream()
                .map(OrderProduct::getProductId)
                .toList();
    }

    public CreateOrderRequest(final List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

}
