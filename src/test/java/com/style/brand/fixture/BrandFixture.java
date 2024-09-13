package com.style.brand.fixture;

import com.style.brand.domain.entity.Brand;
import com.style.brand.presentation.request.CreateBrandRequest;
import com.style.brand.presentation.request.UpdateBrandRequest;
import com.style.member.domain.entity.Member;

import java.util.List;

public class BrandFixture {

    public static final String FIRST_BRAND_NAME = "Brand1";
    public static final String SECOND_BRAND_NAME = "Brand2";

    public static Brand getMockBrand(Member member) {
        return Brand.builder()
                .name("NewBrand")
                .licenseNumber(123456L)
                .phoneNumber(123456L)
                .owner(member)
                .build();
    }

    public static List<Brand> getMockBrands() {
        return List.of(
                Brand.builder().name(FIRST_BRAND_NAME).build(),
                Brand.builder().name(SECOND_BRAND_NAME).build()
        );
    }

    public static CreateBrandRequest getCreateBrandRequest(String name, Long licenseNumber, Long phoneNumber) {
        return new CreateBrandRequest(name, licenseNumber, phoneNumber);
    }

    public static UpdateBrandRequest getUpdateBrandRequest(String name, Long phoneNumber) {
        return new UpdateBrandRequest(name, phoneNumber);
    }

}
