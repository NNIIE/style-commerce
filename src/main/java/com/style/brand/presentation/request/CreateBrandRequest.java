package com.style.brand.presentation.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.style.common.utils.consts.RegexpConstants.*;

@Getter
@NoArgsConstructor
@ToString
public class CreateBrandRequest {

    @NotBlank(message = "이름 필수 입력 입니다.")
    @Size(max = 20, message = "이름은 20글자 이하이어야 합니다.")
    @Pattern(regexp = ENGLISH_KOREAN_REGEXP, message = "이름은 한글 또는 영어이어야 합니다.")
    private String name;

    @NotNull(message = "사업자 등록번호는 필수 입력 입니다.")
    @Positive(message = "사업자 등록번호는 숫자만 가능합니다.")
    private Long licenseNumber;

    @NotNull(message = "사업자 전화번호는 필수 입력 입니다.")
    @Positive(message = "사업자 전화번호는 숫자만 가능합니다.")
    private Long phoneNumber;

    public CreateBrandRequest(
            final String name,
            final Long licenseNumber,
            final Long phoneNumber
    ) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.phoneNumber = phoneNumber;
    }

}
