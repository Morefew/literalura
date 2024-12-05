package com.myco.literalura.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.myco.literalura.model.LibroDTO;


/**
 * project literalura
 *
 * @author Lenny Gonzalez
 * 12:46 PM 11/19/2024 2024
 * @version 1.0.0.0
 * @since 11/19/2024
 */
public class DtoMapper implements IDtoMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T mapToDto(String json, Class<T> clase) {
        try {
            // Create a JsonNode to handle fields that need transformation
            JsonNode jsonNode = objectMapper.readValue(json, JsonNode.class);

            // If the DTO is LibroDTO, handle `idioma` conversion
            if (clase == LibroDTO.class) {
                JsonNode idiomas = jsonNode.get("languages");
                if (idiomas.isArray() && !idiomas.isEmpty()) {
                    ((ObjectNode) jsonNode).put("languages", idiomas.get(0).asText());
                }

                return objectMapper.convertValue(jsonNode, clase);
            }

        } catch (JsonProcessingException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public JsonNode mapToJsonNode(String json) {
        if (json == null || json.isEmpty()) {
            throw new IllegalArgumentException("JSON data is null or empty");
        }
        try {
            return objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }
}
