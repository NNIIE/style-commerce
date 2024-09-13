package com.style.member.fixture;

import com.style.member.domain.entity.Address;
import com.style.member.domain.entity.Member;
import com.style.member.presentation.request.CreateAddressRequest;

public class AddressFixture {

    public static Address getMockAddress(Member member) {
        return Address.builder()
                .member(member)
                .province("testProvince")
                .city("testCity")
                .district("testDistrict")
                .build();
    }

    public static CreateAddressRequest getCreateAddressRequest(String province, String city, String district) {
        return new CreateAddressRequest(province, city, district);
    }

}
