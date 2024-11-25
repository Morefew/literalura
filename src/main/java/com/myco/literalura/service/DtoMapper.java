package com.myco.literalura.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * project literalura
 *
 * @author Lenny Gonzalez
 * 12:46 PM 11/19/2024 2024
 * @version 1.0.0.0
 * @since 11/19/2024
 */
public class DtoMapper implements IDtoMapper {

    private ObjectMapper objectMapper =new ObjectMapper();

    @Override
    public <T> T mapToDto(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode mapToJsonNode(String json) {
        try {
            return objectMapper.readValue(json, JsonNode.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
