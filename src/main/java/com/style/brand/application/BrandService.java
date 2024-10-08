package com.style.brand.application;

import com.style.brand.domain.entity.Brand;
import com.style.brand.infra.repository.BrandRepository;
import com.style.brand.presentation.request.CreateBrandRequest;
import com.style.brand.presentation.request.UpdateBrandRequest;
import com.style.common.annotation.EvictSearchCaches;
import com.style.common.exception.brand.BrandException;
import com.style.common.exception.brand.BrandExceptionCode;
import com.style.member.application.MemberService;
import com.style.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final MemberService memberService;
    private final BrandRepository brandRepository;

    @Transactional(readOnly = true)
    public List<Brand> getMyBrands(final UUID memberId) {
        return brandRepository.findByOwnerId(memberId);
    }

    @Transactional
    public void createBrand(final UUID memberId, final CreateBrandRequest request) {
        final Member member = memberService.getMember(memberId);
        final Brand brand = Brand.builder()
                .owner(member)
                .name(request.getName())
                .licenseNumber(request.getLicenseNumber())
                .phoneNumber(request.getPhoneNumber())
                .build();

        brandRepository.save(brand);
    }

    @Transactional
    public void updateBrand(final Long brandId, final UpdateBrandRequest request) {
        final Brand brand = getBrand(brandId);
        brand.update(request);
    }

    @Transactional
    @EvictSearchCaches
    public void deleteBrand(final UUID memberId, final Long brandId) {
        final Brand brand = brandRepository.findByOwnerIdAndId(memberId, brandId)
                .orElseThrow(() -> new BrandException(BrandExceptionCode.BRAND_NOT_FOUND));

        brandRepository.delete(brand);
    }

    public Brand getBrand(final Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new BrandException(BrandExceptionCode.BRAND_NOT_FOUND));
    }

}
