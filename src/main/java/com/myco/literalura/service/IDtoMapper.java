package com.myco.literalura.service;

/**
 * project literalura
 *
 * @author User
 * 12:48 PM 11/19/2024 2024
 * @Version 1.0.0.0
 * @since 11/19/2024
 */
public interface IDtoMapper {
    <T> T mapToDto(String json, Class<T> clase);
}
