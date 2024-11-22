package com.myco.literalura.model;

import jakarta.persistence.*;

import java.util.List;

/**
 * project literalura
 *
 * @author Lenny Gonzalez
 * 1:07 PM 11/19/2024 2024
 * @version 1.0.0.0
 * @since 11/19/2024
 */

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String idioma;
    private Long descargas;
    private String autor;

    public Book(){}
    public Book(BookDTO bookDTO){
        this.titulo = bookDTO.titulo();
        this.autor = bookDTO.autor();
        this.idioma = bookDTO.idioma();
        this.descargas = bookDTO.descargas();
    }

    @Override
    public String toString() {
        return "Book{" +
                "Id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", idioma='" + idioma + '\'' +
                ", descargas=" + descargas +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autor;
    }

    public void setAutor (String autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Long getDescargas() {
        return descargas;
    }

    public void setDescargas(Long descargas) {
        this.descargas = descargas;
    }
}
