package com.style.product.presentation.request;

import com.style.product.domain.ProductCategory;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

import static com.style.common.utils.Constants.ENGLISH_KOREAN_REGEXP;

@Getter
@NoArgsConstructor
@ToString
public class CreateProductRequest {

    @NotNull(message = "브랜드 ID는 필수 입력 입니다.")
    @Positive(message = "브랜드 ID는 숫자만 가능합니다.")
    private Long brandId;

    @NotNull(message = "카테고리는 필수 입력 입니다.")
    private ProductCategory category;

    @NotBlank(message = "이름 필수 입력 입니다.")
    @Size(max = 20, message = "이름은 20글자 이하이어야 합니다.")
    @Pattern(regexp = ENGLISH_KOREAN_REGEXP, message = "이름은 한글 또는 영어이어야 합니다.")
    private String name;

    @NotNull(message = "가격은 필수 입력 입니다.")
    @Positive(message = "가격은 숫자만 가능합니다.")
    private BigDecimal price;

    @NotNull(message = "수량은 필수 입력 입니다.")
    @Positive(message = "수량은 숫자만 가능합니다.")
    private Integer quantity;

    public CreateProductRequest(
            final Long brandId,
            final ProductCategory category,
            final String name,
            final BigDecimal price,
            final Integer quantity
    ) {
        this.brandId = brandId;
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

}
