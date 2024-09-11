package com.style.product.application;

import com.style.brand.application.BrandService;
import com.style.brand.domain.entity.Brand;
import com.style.common.exception.product.ProductException;
import com.style.common.exception.product.ProductExceptionCode;
import com.style.product.domain.entity.Product;
import com.style.product.infra.ProductRepository;
import com.style.product.presentation.request.CreateProductRequest;
import com.style.product.presentation.request.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final BrandService brandService;
    private final ProductRepository productRepository;

    @Transactional
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
    public void updateProduct(final Long productId, final UpdateProductRequest request) {
        final Product product = getProduct(productId);
        product.update(request);
    }

    @Transactional
    public void deleteProduct(final Long id) {
        productRepository.deleteById(id);
    }

    public Product getProduct(final Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductExceptionCode.PRODUCT_NOT_FOUND));
    }

}
