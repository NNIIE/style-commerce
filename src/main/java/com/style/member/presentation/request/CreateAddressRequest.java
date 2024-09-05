package com.style.member.presentation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

import static com.style.member.utils.MemberConst.ADDRESS_REGEXP;

@Getter
@ToString
public class CreateAddressRequest {

    @NotBlank(message = "도는 필수 입력 입니다.")
    @Size(max = 10, message = "도는 10글자 이하이어야 합니다.")
    @Pattern(regexp = ADDRESS_REGEXP, message = "주소는 한글이어야 합니다.")
    private String province;

    @NotBlank(message = "시는 필수 입력 입니다.")
    @Size(max = 10, message = "시는 10글자 이하이어야 합니다.")
    @Pattern(regexp = ADDRESS_REGEXP, message = "주소는 한글이어야 합니다.")
    private String city;

    @NotBlank(message = "구는 필수 입력 입니다.")
    @Size(max = 10, message = "구는 10글자 이하이어야 합니다.")
    @Pattern(regexp = ADDRESS_REGEXP, message = "주소는 한글이어야 합니다.")
    private String district;

}
