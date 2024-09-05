package com.style.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.style.common.exception.parse.ParseException;
import com.style.common.exception.parse.ParseExceptionCode;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> String objectToJson(T object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new ParseException(ParseExceptionCode.JSON_PARSER_EXCEPTION);
        }
    }

}
