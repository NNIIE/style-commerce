package com.style.product.application;

import com.style.brand.application.BrandService;
import com.style.brand.domain.entity.Brand;
import com.style.common.annotation.EvictSearchCaches;
import com.style.common.exception.product.ProductException;
import com.style.common.exception.product.ProductExceptionCode;
import com.style.product.domain.entity.Product;
import com.style.product.infra.ProductRepository;
import com.style.product.presentation.request.CreateProductRequest;
import com.style.product.presentation.request.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final BrandService brandService;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> getProductsByBrand(final Long brandId) {
        return productRepository.findByBrandId(brandId);
    }

    @Transactional
    @EvictSearchCaches
    public void createProduct(final CreateProductRequest request) {
        final Brand brand = brandService.getBrand(request.getBrandId());
        final Product product = Product.builder()
                .brand(brand)
                .category(request.getCategory())
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();

        productRepository.save(product);
    }

    @Transactional
    @EvictSearchCaches
    public void updateProduct(final Long productId, final UpdateProductRequest request) {
        final Product product = getProduct(productId);
        product.update(request);
    }

    @Transactional
    @EvictSearchCaches
    public void deleteProduct(final Long id) {
        productRepository.deleteById(id);
    }

    public Product getProduct(final Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductExceptionCode.PRODUCT_NOT_FOUND));
    }

}
