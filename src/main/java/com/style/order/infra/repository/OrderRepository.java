package com.style.order.infra.repository;

import com.style.order.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

    Optional<Order> findByIdAndMemberId(Long orderId, UUID memberId);

}
