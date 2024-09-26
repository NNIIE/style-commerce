package com.style.common.domain;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.util.StringUtils;

import java.util.Optional;

public interface QueryDslConditionBuilder {

    default BooleanExpression andLikeIgnoreCase(StringPath path, String value) {
        return Optional.ofNullable(value)
                .filter(StringUtils::hasText)
                .map(path::containsIgnoreCase)
                .orElse(null);
    }

    default <T> BooleanExpression andEquals(SimpleExpression<T> path, T value) {
        return Optional.ofNullable(value)
                .map(path::eq)
                .orElse(null);
    }

}
