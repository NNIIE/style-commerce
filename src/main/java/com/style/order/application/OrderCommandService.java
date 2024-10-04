package com.style.order.application;

import com.style.common.exception.order.OrderException;
import com.style.common.exception.order.OrderExceptionCode;
import com.style.common.exception.product.ProductException;
import com.style.common.exception.product.ProductExceptionCode;
import com.style.member.application.MemberService;
import com.style.member.domain.entity.Member;
import com.style.order.domain.OrderStatus;
import com.style.order.domain.entity.Order;
import com.style.order.domain.entity.OrderItem;
import com.style.order.infra.repository.OrderRepository;
import com.style.order.presentation.request.CreateOrderRequest;
import com.style.order.presentation.request.OrderProduct;
import com.style.product.domain.entity.Product;
import com.style.product.infra.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderCommandService {

    private final MemberService memberService;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void cancelOrder(final Long orderId, final UUID memberId) {
        final Order order = orderRepository.findByIdAndMemberId(orderId, memberId)
                .orElseThrow(() -> new OrderException(OrderExceptionCode.NOT_FOUND_ORDER));

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new OrderException(OrderExceptionCode.NOT_CANCEL_ORDER);
        }

        order.setStatus(OrderStatus.CANCELLED);
    }

    @Transactional
    public void createOrder(final CreateOrderRequest request, final UUID memberId) {
        final Member member = memberService.getMemberWithAddresses(memberId);
        final Order order = initOrder(request.getAddressId(), member);
        final List<Product> products = productRepository.findByIdIn(request.getOrderProductIds());
        productQuantityCheckAndGetOrderItems(request.getOrderProducts(), products, order);

        orderRepository.save(order);
    }

    private Order initOrder(final Long addressId, final Member member) {
        return Order.builder()
                .member(member)
                .address(member.getAddressById(addressId))
                .status(OrderStatus.PENDING)
                .build();
    }

    private void productQuantityCheckAndGetOrderItems(final List<OrderProduct> orderProducts, final List<Product> products, final Order order) {
        orderProducts.forEach(orderProduct -> {
            final Product product = findProduct(products, orderProduct);
            checkStockAndReflect(orderProduct, product);
            final OrderItem orderItem = createOrderItem(order, orderProduct, product);

            order.addOrderItem(orderItem);
        });
    }

    private Product findProduct(final List<Product> products, final OrderProduct orderProduct) {
        return products.stream()
                .filter(product -> product.getId().equals(orderProduct.getProductId()))
                .findFirst()
                .orElseThrow(() -> new ProductException(ProductExceptionCode.PRODUCT_NOT_FOUND));
    }

    private void checkStockAndReflect(final OrderProduct orderProduct, final Product product) {
        if (product.getQuantity() < orderProduct.getQuantity()) {
            throw new OrderException(OrderExceptionCode.LOW_STOCK);
        }
        product.setQuantity(product.getQuantity() - orderProduct.getQuantity());
    }

    private OrderItem createOrderItem(final Order order, final OrderProduct orderProduct, final Product product) {
        return OrderItem.builder()
                .product(product)
                .order(order)
                .quantity(orderProduct.getQuantity())
                .build();
    }

}