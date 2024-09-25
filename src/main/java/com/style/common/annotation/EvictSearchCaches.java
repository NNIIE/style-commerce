package com.style.common.annotation;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.style.common.utils.Constants.LOWEST_PRODUCTS_BY_CATEGORY_AND_TOTAL_PRICE;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Caching(evict = {
        @CacheEvict(value = LOWEST_PRODUCTS_BY_CATEGORY_AND_TOTAL_PRICE, allEntries = true)
})
public @interface EvictSearchCaches {
}
