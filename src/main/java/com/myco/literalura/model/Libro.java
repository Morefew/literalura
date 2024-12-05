package com.myco.literalura.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * project literalura
 *
 * @author Lenny Gonzalez
 * 1:07 PM 11/19/2024 2024
 * @version 1.0.0.0
 * @since 11/19/2024
 */

@Entity
@Table(name = "libro")
public class Libro implements Serializable {
    @Id
    private Long libroId;
    @Column(unique = true)
    private String titulo;
    private String idioma;
    private Long descargas;
    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "autoreslibros",
            joinColumns = @JoinColumn(name = "libroId"),
            inverseJoinColumns = @JoinColumn(name = "autorId"))
    private List<Autor> autores;

    public Libro() {
    }

    public Libro(LibroDTO libroDTO) {
        this.libroId = libroDTO.libroId();
        this.titulo = libroDTO.titulo();
        setAutores(libroDTO.autores());
        this.idioma = libroDTO.idioma();
        this.descargas = libroDTO.descargas();
    }

    @Override
    public String toString() {
        return """
                
                \u001B[40m \u001B[33;1m Datos sobre el libro:\u001B[0m %s
                \u001B[34m Id: %d
                \u001B[34m Autore(s):\u001B[0m %s
                \u001B[34m Idioma:\u001B[0m %s
                \u001B[34m Descargas:\u001B[0m %d
                """.formatted(titulo != null ? titulo : "Desconocido", libroId,
                autores != null ? autores : "Desconocido/s",
                idioma != null ? idioma : "Desconocido",
                descargas != null ? descargas : 0);
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long id) {
        this.libroId = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores.toString();
    }

    public void setAutores(List<AutorDTO> autores) {
        this.autores = autores.stream().map(a -> new Autor(a)).collect(Collectors.toList());
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
