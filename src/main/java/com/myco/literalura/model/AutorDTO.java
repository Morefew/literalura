package com.myco.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * project literalura
 *
 * @author Lenny Gonzalez
 * 1:29 PM 11/19/2024 2024
 * @version 1.0.0.0
 * @since 12/02/2024
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorDTO(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") int nacimiento,
        @JsonAlias("death_year") int fallecimiento
){

    @Override
    public String toString() {
        return """
                \u001B[40m \u001B[33;1m Datos sobre el autor:\u001B[0m
                \u001B[34m Nombre:\u001B[0m %s
                \u001B[34m Nacimiento:\u001B[0m %d
                \u001B[34m Fallecimiento:\u001B[0m %d
                """.formatted(
                nombre != null ? nombre : "Desconocido",
                nacimiento,
                fallecimiento);
    }
}
