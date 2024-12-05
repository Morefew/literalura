package com.myco.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * project literalura
 *
 * @author Lenny Gonzalez
 * 1:29 PM 11/19/2024 2024
 * @version 1.0.0.0
 * @since 11/19/2024
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroDTO(
        @JsonAlias("id") Long libroId,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<AutorDTO> autores,
        @JsonAlias("languages") String idioma,
        @JsonAlias("download_count") Long descargas
){
    @Override
    public String toString() {
        return """
                
                \u001b[40m Datos sobre el libro \u001B[33;1m libroId: %d \u001B[0m
                \u001B[34m Título:\u001B[0m %s  \u001B[34m Autores:\u001B[0m %s  \u001B[34m Idioma:\u001B[0m %s  \u001B[34m Descargas:\u001B[0m %d
                """.formatted(libroId,
                titulo != null ? titulo : "Desconocido",
                autores != null ? autores : "Desconocido/s",
                idioma != null ? idioma : "Desconocido",
                descargas != null ? descargas : 0);
    }
}
