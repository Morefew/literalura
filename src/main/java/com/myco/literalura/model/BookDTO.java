package com.myco.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * project literalura
 *
 * @author Lenny Gonzalez
 * 1:29 PM 11/19/2024 2024
 * @Version 1.0.0.0
 * @since 11/19/2024
 */

//API Object
//  "id": <number of Project Gutenberg ID>,
//  "title": <string>,
//  "subjects": <array of strings>,
//  "authors": <array of Persons>,
//  "translators": <array of Persons>,
//  "bookshelves": <array of strings>,
//  "languages": <array of strings>,
//  "copyright": <boolean or null>,
//  "media_type": <string>,
//  "formats": <Format>,
//  "download_count": <number>

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
        @JsonAlias("id") Long id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") String autor,
        @JsonAlias("languages") String idioma,
        @JsonAlias("download_count") Long descargas
){
    @Override
    public String toString() {
        return """
                
                \u001b[40m Datos sobre el libro \u001B[33;1m id: %d \u001B[0m
                \u001B[34m Título:\u001B[0m %s  \u001B[34m Autor:\u001B[0m %s  \u001B[34m Idioma:\u001B[0m %s  \u001B[34m Descargas:\u001B[0m %d
                """.formatted(id,
                titulo != null ? titulo : "Desconocido",
                autor != null ? autor: "Desconocido",
                idioma != null ? idioma : "Desconocido",
                descargas != null ? descargas : 0);
    }
}
