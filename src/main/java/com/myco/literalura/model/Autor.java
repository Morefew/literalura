package com.myco.literalura.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * project literalura
 *
 * @author Lenny Gonzalez
 * 4:59 PM 11/22/2024 2024
 * @version 1.0.0.0
 * @since 11/22/2024
 */

@Entity
@Table(name = "autor")
public class Autor implements Serializable {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autorId;
    @Column(unique = true)
    private String nombre;
    private int nacimiento;
    private int fallecimiento;
    @ManyToMany
    private List<Libro> libros = new ArrayList<>();

    public Autor(){}
    public Autor(AutorDTO autorDTO){
        this.nombre = autorDTO.nombre();
        this.nacimiento = autorDTO.nacimiento();
        this.fallecimiento = autorDTO.fallecimiento();
    }

    public Long getAutorId() {
        return autorId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(int nacimiento) {
        this.nacimiento = nacimiento;
    }

    public int getFallecimiento() {
        return fallecimiento;
    }

    public void setFallecimiento(int fallecimiento) {
        this.fallecimiento = fallecimiento;
    }

    public List<Libro> getBooks() {
        return libros;
    }

    public void setBooks(List<Libro> libros) {
        this.libros = libros;
    }

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


