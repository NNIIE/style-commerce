package com.style.order.presentation.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class OrderProduct {

    @NotNull(message = "상품 ID는 필수 입력 입니다.")
    @Positive(message = "상품 ID는 숫자만 가능합니다.")
    private Long productId;

    @NotNull(message = "수량은 필수 입력 입니다.")
    @Positive(message = "수량은 숫자만 가능합니다.")
    private Integer quantity;

    public OrderProduct(final Long productId, final Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

}
