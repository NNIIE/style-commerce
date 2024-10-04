package com.style.order.application;

import com.style.common.domain.PagedResponse;
import com.style.order.domain.SearchOrder;
import com.style.order.domain.SearchOrderProduct;
import com.style.order.domain.entity.Order;
import com.style.order.domain.entity.OrderItem;
import com.style.order.infra.repository.OrderItemRepository;
import com.style.order.infra.repository.OrderRepository;
import com.style.order.presentation.request.SearchOrdersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderQueryService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional(readOnly = true)
    public PagedResponse<SearchOrder> searchOrders(final SearchOrdersRequest request, final Pageable pageable, final UUID memberId) {
        final Page<Order> pageOrders = orderRepository.findOrdersByConditions(request, pageable, memberId);
        final List<SearchOrder> searchOrders = getSearchOrders(pageOrders);

        return new PagedResponse<>(
                searchOrders,
                pageOrders.getTotalPages(),
                pageOrders.getTotalElements(),
                pageOrders.getNumber(),
                pageOrders.getSize()
        );
    }

    private List<SearchOrder> getSearchOrders(final Page<Order> pageOrders) {
        return pageOrders.getContent().stream()
                .map(order -> SearchOrder.builder()
                        .orderId(order.getId())
                        .address(order.getAddress())
                        .status(order.getStatus())
                        .orderProducts(getOrderProduct(order.getId()))
                        .build())
                .toList();
    }

    private List<SearchOrderProduct> getOrderProduct(final Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);

        return orderItems.stream()
                .map(orderItem -> new SearchOrderProduct(
                        orderItem.getId(),
                        orderItem.getProduct().getId(),
                        orderItem.getProduct().getCategory(),
                        orderItem.getProduct().getName(),
                        orderItem.getProduct().getPrice(),
                        orderItem.getQuantity()))
                .toList();
    }

}
