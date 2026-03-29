package com.qa.api.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T deserelise(Response response, Class<T> targetClass) throws JsonProcessingException {
        return objectMapper.readValue(response.getBody().asString(),targetClass);
    }
}
