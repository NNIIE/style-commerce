package com.style.order.fixture;

import com.style.order.presentation.request.CreateOrderRequest;
import com.style.order.presentation.request.OrderProduct;

import java.util.Collections;
import java.util.List;

public class OrderFixture {

    public static List<OrderProduct> getOrderProduct() {
        return Collections.singletonList(new OrderProduct(1L, 10));
    }

    public static CreateOrderRequest createOrderRequest() {
        return new CreateOrderRequest(getOrderProduct());
    }

}
