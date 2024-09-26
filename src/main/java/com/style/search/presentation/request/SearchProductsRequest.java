package com.style.search.presentation.request;

import com.style.product.domain.ProductCategory;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import static com.style.common.utils.consts.RegexpConstants.ENGLISH_KOREAN_REGEXP;

@Getter @Setter
@ToString
public class SearchProductsRequest {

    @Size(max = 20, message = "이름은 20글자 이하이어야 합니다.")
    @Pattern(regexp = ENGLISH_KOREAN_REGEXP, message = "이름은 한글 또는 영어이어야 합니다.")
    private String productName;

    @Positive(message = "브랜드 ID는 숫자만 가능합니다.")
    private Long brandId;

    private ProductCategory category;

    public boolean hasNoSearchCriteria() {
        return !StringUtils.hasText(productName) && (brandId == null) && (category == null);
    }

}
