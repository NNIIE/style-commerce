package com.style.order.infra.repository;

import com.style.order.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @EntityGraph(attributePaths = {"product"})
    List<OrderItem> findByOrderId(Long orderId);

}
