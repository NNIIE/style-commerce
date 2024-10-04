package com.style.order.infra.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.style.common.domain.QueryDslConditionBuilder;
import com.style.order.domain.entity.Order;
import com.style.order.presentation.request.SearchOrdersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.style.member.domain.entity.QAddress.address;
import static com.style.order.domain.entity.QOrder.order;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom, QueryDslConditionBuilder {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Order> findOrdersByConditions(final SearchOrdersRequest request, final Pageable pageable, final UUID memberId) {
        List<Order> products = jpaQueryFactory
                .select(order)
                .from(order)
                .innerJoin(order.address, address).fetchJoin()
                .where(
                        andEquals(order.member.id, memberId),
                        andEquals(order.status, request.getStatus()),
                        andDateBetween(order.createdAt, request.getCreatedAtFrom(), request.getCreatedAtTo())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        final JPAQuery<Long> countQuery = getCount(request);

        return PageableExecutionUtils.getPage(products, pageable, countQuery::fetchOne);
    }

    private JPAQuery<Long> getCount(final SearchOrdersRequest request) {
        return jpaQueryFactory
                .select(order.count())
                .from(order)
                .innerJoin(order.address, address)
                .where(
                        andEquals(order.status, request.getStatus())
                );
    }

}
