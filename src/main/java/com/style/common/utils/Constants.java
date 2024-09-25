package com.style.common.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    public static final String SESSION_MEMBER_KEY = "member";

    public static final String LOWEST_PRODUCTS_BY_CATEGORY_AND_TOTAL_PRICE = "lowestProductsByCategoryAndTotalPrice";

    public static final String EMAIL_REGEXP = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$";
    public static final String ENGLISH_KOREAN_REGEXP = "^[가-힣a-zA-Z]+$";
    public static final String KOREAN_REGEXP = "^[가-힣]+$";
    public static final String SPECIAL_CHARACTERS_REGEXP = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{10,16}";

}
