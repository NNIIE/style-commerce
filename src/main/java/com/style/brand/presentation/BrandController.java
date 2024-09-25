package com.style.brand.presentation;

import com.style.brand.application.BrandService;
import com.style.brand.domain.entity.Brand;
import com.style.brand.presentation.request.CreateBrandRequest;
import com.style.brand.presentation.request.UpdateBrandRequest;
import com.style.common.domain.SessionMember;
import com.style.common.annotation.CurrentAdminMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Brand")
@RequestMapping("/brand")
public class BrandController {

    private final BrandService brandService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "나의 브랜드 목록 조회")
    public List<Brand> getMyBrands(@Parameter(hidden = true) @CurrentAdminMember final SessionMember member) {
        return brandService.getMyBrands(member.id());
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "브랜드 등록")
    public void createBrand(
            @RequestBody @Valid final CreateBrandRequest request,
            @Parameter(hidden = true) @CurrentAdminMember final SessionMember member
    ) {
        brandService.createBrand(member.id(), request);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "브랜드 정보 변경")
    public void updateBrand(
            @PathVariable final Long id,
            @RequestBody @Valid final UpdateBrandRequest request,
            @Parameter(hidden = true) @CurrentAdminMember final SessionMember member
    ) {
        brandService.updateBrand(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "브랜드 삭제")
    public void deleteBrand(
            @PathVariable final Long id,
            @Parameter(hidden = true) @CurrentAdminMember final SessionMember member
    ) {
        brandService.deleteBrand(member.id(), id);
    }

}
