package com.style.order.application;

import com.style.common.exception.order.OrderException;
import com.style.member.domain.entity.Member;
import com.style.member.fixture.MemberFixture;
import com.style.order.domain.OrderStatus;
import com.style.order.domain.entity.Order;
import com.style.order.fixture.OrderFixture;
import com.style.order.infra.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

public class CancelOrderTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderCommandService orderCommandService;

    private Order mockOrder;
    private final Long orderId = 1L;
    private final UUID memberId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Member mockMember = MemberFixture.getMockMember();
        mockMember.setId(memberId);
        mockOrder = OrderFixture.getOrder(mockMember, OrderStatus.PENDING);
        mockOrder.setId(orderId);
    }

    @Test
    @DisplayName("cancelOrder() - 성공")
    void cancelOrderSuccessTest() {
        // Given
        when(orderRepository.findByIdAndMemberId(orderId, memberId)).thenReturn(Optional.ofNullable(mockOrder));

        // When
        orderCommandService.cancelOrder(orderId, memberId);

        // Then
        assertAll(
                () -> verify(orderRepository, times(1)).findByIdAndMemberId(orderId, memberId),
                () -> assertEquals(mockOrder.getStatus(), OrderStatus.CANCELLED)
        );
    }

    @Test
    @DisplayName("cancelOrder() - 상태가 PENDING이 아님")
    void cancelOrderFailTest_NotPending() {
        // Given
        mockOrder.setStatus(OrderStatus.SHIPPED);
        when(orderRepository.findByIdAndMemberId(orderId, memberId)).thenReturn(Optional.ofNullable(mockOrder));

        // When & Then
        assertAll(
                () -> assertThrows(OrderException.class, () -> orderCommandService.cancelOrder(orderId, memberId)),
                () -> verify(orderRepository, times(1)).findByIdAndMemberId(orderId, memberId),
                () -> assertEquals(mockOrder.getStatus(), OrderStatus.SHIPPED)
        );
    }

    @Test
    @DisplayName("cancelOrder() - 존재하지 않는 주문")
    void cancelOrderFailTest_OrderNotFound() {
        // Given
        when(orderRepository.findByIdAndMemberId(orderId, memberId)).thenReturn(Optional.empty());

        // When & Then
        assertAll(
                () -> assertThrows(OrderException.class, () -> orderCommandService.cancelOrder(orderId, memberId)),
                () -> verify(orderRepository, times(1)).findByIdAndMemberId(orderId, memberId)
        );
    }

}
