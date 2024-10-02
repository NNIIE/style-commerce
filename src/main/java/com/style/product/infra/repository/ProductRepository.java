package com.style.product.infra.repository;

import com.style.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByBrandId(Long brandId);

    List<Product> findByIdIn(List<Long> productIds);

}
