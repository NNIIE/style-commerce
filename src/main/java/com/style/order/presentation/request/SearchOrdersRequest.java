package com.style.order.presentation.request;

import com.style.order.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
public class SearchOrdersRequest {

    private OrderStatus status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime createdAtFrom; // "2024-01-01T00:00:00"

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime createdAtTo; // "2026-01-01T00:00:00"

}
