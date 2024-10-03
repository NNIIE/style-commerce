package com.style.order.presentation.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull(message = "주소 ID는 필수 입력 입니다.")
    @Positive(message = "주소 ID는 숫자만 가능합니다.")
    private Long addressId;

    public List<Long> getOrderProductIds() {
        return orderProducts.stream()
                .map(OrderProduct::getProductId)
                .toList();
    }

    public CreateOrderRequest(final List<OrderProduct> orderProducts, final Long addressId) {
        this.orderProducts = orderProducts;
        this.addressId = addressId;
    }

}
