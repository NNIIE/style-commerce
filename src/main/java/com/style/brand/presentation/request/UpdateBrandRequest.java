package com.style.brand.presentation.request;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.style.common.utils.consts.RegexpConstants.ENGLISH_KOREAN_REGEXP;

@Getter
@NoArgsConstructor
@ToString
public class UpdateBrandRequest {

    @Size(max = 20, message = "이름은 20글자 이하이어야 합니다.")
    @Pattern(regexp = ENGLISH_KOREAN_REGEXP, message = "이름은 한글 또는 영어이어야 합니다.")
    private String name;

    @Positive(message = "사업자 전화번호는 숫자만 가능합니다.")
    private Long phoneNumber;

    public boolean isNameUpdate() {
        return !StringUtils.isEmpty(name);
    }

    public boolean isPhoneNumberUpdate() {
        return phoneNumber != null;
    }

    public UpdateBrandRequest(final String name, final Long phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

}
