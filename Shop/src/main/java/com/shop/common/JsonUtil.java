package com.shop.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.shop.dto.CoupangDto;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    /**
     * 객체 리스트를 JSON 배열 형태의 문자열로 변환한 후,
     * 각 객체를 개행으로 구분하여 출력합니다.
     *
     * @param dto 변환할 객체 리스트
     * @return JSON 배열 형태의 문자열에서 `[`와 `]`를 제거한 단일 객체 형태의 JSON 문자열
     */
    public static String convertListToJson(CoupangDto dto) {
        String json = "";

        try {
            // 객체를 JSON 문자열로 변환
            json = objectMapper.writeValueAsString(dto);
            // JSON 문자열에서 가장 앞과 뒤의 대괄호 제거
            if (json.startsWith("[") && json.endsWith("]")) {
                json = json.substring(1, json.length() - 1); // 첫 번째와 마지막 대괄호 제거
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

}