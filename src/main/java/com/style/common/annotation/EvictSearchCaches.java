package com.style.common.annotation;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.style.common.utils.consts.KeyConstants.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Caching(evict = {
        @CacheEvict(value = LOWEST_PRODUCTS_BY_CATEGORY_AND_TOTAL_PRICE, allEntries = true),
        @CacheEvict(value = CHEAPEST_BRAND_FOR_ALL_CATEGORY, allEntries = true)
})
public @interface EvictSearchCaches {
}
