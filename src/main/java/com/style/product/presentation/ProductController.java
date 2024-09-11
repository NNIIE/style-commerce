package com.style.product.presentation;

import com.style.common.domain.SessionMember;
import com.style.member.domain.CurrentAdminMember;
import com.style.product.application.ProductService;
import com.style.product.domain.entity.Product;
import com.style.product.presentation.request.CreateProductRequest;
import com.style.product.presentation.request.UpdateProductRequest;
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
@Tag(name = "Product")
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/brand/{brandId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "브랜드의 상품 목록 조회")
    public List<Product> getProductsByBrand(
            @PathVariable final Long brandId,
            @Parameter(hidden = true) @CurrentAdminMember final SessionMember member
    ) {
        return productService.getProductsByBrand(brandId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "상품 등록")
    public void createProduct(
            @RequestBody @Valid final CreateProductRequest request,
            @Parameter(hidden = true) @CurrentAdminMember final SessionMember member
    ) {
        productService.createProduct(request);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품 정보 변경")
    public void updateProduct(
            @PathVariable final Long id,
            @RequestBody @Valid final UpdateProductRequest request,
            @Parameter(hidden = true) @CurrentAdminMember final SessionMember member
    ) {
        productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "상품 삭제")
    public void deleteProduct(
            @PathVariable final Long id,
            @Parameter(hidden = true) @CurrentAdminMember final SessionMember member
    ) {
        productService.deleteProduct(id);
    }

}
