package com.style.product.presentation.request;

import com.style.product.domain.ProductCategory;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

import static com.style.common.utils.consts.RegexpConstants.ENGLISH_KOREAN_REGEXP;

@Getter
@NoArgsConstructor
@ToString
public class UpdateProductRequest {

    private ProductCategory category;

    @Size(max = 20, message = "이름은 20글자 이하이어야 합니다.")
    @Pattern(regexp = ENGLISH_KOREAN_REGEXP, message = "이름은 한글 또는 영어이어야 합니다.")
    private String name;

    @Positive(message = "가격은 숫자만 가능합니다.")
    private BigDecimal price;

    @Positive(message = "수량은 숫자만 가능합니다.")
    private Integer quantity;

    public boolean isCategoryUpdate() {
        return category != null;
    }

    public boolean isNameUpdate() {
        return !StringUtils.isEmpty(name);
    }

    public boolean isPriceUpdate() {
        return price != null;
    }

    public boolean isQuantityUpdate() {
        return quantity != null;
    }

    public UpdateProductRequest(
            final ProductCategory category,
            final String name,
            final BigDecimal price,
            final Integer quantity
    ) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

}
