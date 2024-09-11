package com.style.brand.infra.repository;

import com.style.brand.domain.entity.Brand;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    @EntityGraph(attributePaths = {"products"})
    List<Brand> findByOwnerId(UUID ownerId);

    @EntityGraph(attributePaths = {"products"})
    Optional<Brand> findByOwnerIdAndId(UUID ownerId, Long brandId);

}
