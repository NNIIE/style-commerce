package com.style.brand.presentation;

import com.style.brand.application.BrandService;
import com.style.brand.presentation.request.CreateBrandRequest;
import com.style.brand.presentation.request.UpdateBrandRequest;
import com.style.member.domain.CurrentAdminMember;
import com.style.member.domain.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Brand")
@RequestMapping("/brand")
public class BrandController {

    private final BrandService brandService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "브랜드 등록")
    public void createBrand(@RequestBody @Valid final CreateBrandRequest request, @CurrentAdminMember final Member member) {
        brandService.createBrand(member.getId(), request);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "브랜드 정보 변경")
    public void updateBrand(
            @PathVariable final Long id,
            @RequestBody @Valid final UpdateBrandRequest request,
            @CurrentAdminMember final Member member
    ) {
        brandService.updateBrand(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "브랜드 삭제")
    public void deleteBrand(@PathVariable final Long id, @CurrentAdminMember final Member member) {
        brandService.deleteBrand(member.getId(), id);
    }

}
