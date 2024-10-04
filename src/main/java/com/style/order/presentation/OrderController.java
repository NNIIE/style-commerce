package com.style.order.presentation;

import com.style.common.annotation.CurrentMember;
import com.style.common.domain.PagedResponse;
import com.style.common.domain.SessionMember;
import com.style.order.application.OrderCommandService;
import com.style.order.application.OrderQueryService;
import com.style.order.domain.SearchOrder;
import com.style.order.presentation.request.CreateOrderRequest;
import com.style.order.presentation.request.SearchOrdersRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Order")
@RequestMapping("/order")
public class OrderController {

    private final OrderQueryService orderQueryService;
    private final OrderCommandService orderCommandService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "주문 조회")
    public PagedResponse<SearchOrder> searchOrders(
            @Valid @ModelAttribute final SearchOrdersRequest request,
            @PageableDefault Pageable pageable,
            @Parameter(hidden = true) @CurrentMember final SessionMember member
    ) {
        return orderQueryService.searchOrders(request, pageable, member.id());
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "주문 생성")
    public void createOrder(
            @RequestBody @Valid final CreateOrderRequest request,
            @Parameter(hidden = true) @CurrentMember final SessionMember member
    ) {
        orderCommandService.createOrder(request, member.id());
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "주문 취소")
    public void cancelOrder(
            @PathVariable final Long id,
            @Parameter(hidden = true) @CurrentMember final SessionMember member
    ) {
        orderCommandService.cancelOrder(id, member.id());
    }

}
