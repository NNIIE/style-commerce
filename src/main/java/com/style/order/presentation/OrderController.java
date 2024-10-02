package com.style.order.presentation;

import com.style.common.annotation.CurrentMember;
import com.style.common.domain.SessionMember;
import com.style.order.application.OrderService;
import com.style.order.presentation.request.CreateOrderRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Order")
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "주문 생성")
    public void createOrder(
            @RequestBody @Valid final CreateOrderRequest request,
            @Parameter(hidden = true) @CurrentMember final SessionMember member
    ) {
        orderService.createOrder(request, member.id());
    }

}
