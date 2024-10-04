package com.style.order.infra.repository;

import com.style.order.domain.entity.Order;
import com.style.order.presentation.request.SearchOrdersRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OrderRepositoryCustom {

    Page<Order> findOrdersByConditions(SearchOrdersRequest request, Pageable pageable, UUID memberId);

}
