package com.style.order.application;

import com.style.brand.fixture.BrandFixture;
import com.style.common.exception.order.OrderException;
import com.style.common.exception.product.ProductException;
import com.style.member.application.MemberService;
import com.style.member.domain.entity.Member;
import com.style.member.fixture.MemberFixture;
import com.style.order.domain.entity.Order;
import com.style.order.fixture.OrderFixture;
import com.style.order.infra.repository.OrderRepository;
import com.style.order.presentation.request.CreateOrderRequest;
import com.style.product.domain.entity.Product;
import com.style.product.fixture.ProductFixture;
import com.style.product.infra.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class CreateOrderTest {

    @Mock
    private MemberService memberService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Member mockMember;
    private Product mockProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMember = MemberFixture.getMockMember();
        mockProduct = ProductFixture.getMockProduct(BrandFixture.getMockBrand(MemberFixture.getMockMember()));
        mockProduct.setId(1L);
    }

    @Test
    @DisplayName("createOrder() - 성공")
    void createOrderSuccessTest() {
        // Given
        UUID memberId = mockMember.getId();
        CreateOrderRequest request = OrderFixture.createOrderRequest();

        when(memberService.getMember(memberId)).thenReturn(mockMember);
        when(productRepository.findByIdIn(anyList())).thenReturn(List.of(mockProduct));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        orderService.createOrder(request, memberId);

        // Then
        assertAll(
                () -> verify(memberService, times(1)).getMember(memberId),
                () -> verify(productRepository, times(1)).findByIdIn(anyList()),
                () -> verify(orderRepository, times(1)).save(any(Order.class)),
                () -> verify(productRepository).findByIdIn(argThat(ids -> ids.contains(mockProduct.getId()))),
                () -> verify(orderRepository).save(argThat(order ->
                        order.getMember().equals(mockMember) &&
                                order.getOrderItems().stream().allMatch(orderItem ->
                                        orderItem.getProduct().equals(mockProduct) &&
                                                orderItem.getQuantity().equals(request.getOrderProducts().get(0).getQuantity()))
                ))
        );
    }

    @Test
    @DisplayName("createOrder() - 재고 부족")
    void createOrderFailTest_LowStock() {
        // Given
        UUID memberId = mockMember.getId();
        CreateOrderRequest request = OrderFixture.createOrderRequest();
        mockProduct.setQuantity(5);

        when(memberService.getMember(memberId)).thenReturn(mockMember);
        when(productRepository.findByIdIn(anyList())).thenReturn(List.of(mockProduct));

        // When & Then
        assertAll(
                () -> assertThrows(OrderException.class, () -> orderService.createOrder(request, memberId)),
                () -> verify(memberService, times(1)).getMember(memberId),
                () -> verify(productRepository, times(1)).findByIdIn(anyList()),
                () -> verify(orderRepository, never()).save(any(Order.class))
        );
    }

    @Test
    @DisplayName("createOrder() - 존재하지 않는 상품")
    void createOrderFailTest_ProductNotFound() {
        // Given
        UUID memberId = mockMember.getId();
        CreateOrderRequest request = OrderFixture.createOrderRequest();

        when(memberService.getMember(memberId)).thenReturn(mockMember);
        when(productRepository.findByIdIn(anyList())).thenReturn(List.of());

        // When & Then
        assertAll(
                () -> assertThrows(ProductException.class, () -> orderService.createOrder(request, memberId)),
                () -> verify(memberService, times(1)).getMember(memberId),
                () -> verify(productRepository, times(1)).findByIdIn(anyList()),
                () -> verify(orderRepository, never()).save(any(Order.class))
        );
    }

}