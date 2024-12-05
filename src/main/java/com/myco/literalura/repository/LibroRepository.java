package com.myco.literalura.repository;

import com.myco.literalura.model.Autor;
import com.myco.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * project literalura
 *
 * @author User
 * 1:06 PM 11/19/2024 2024
 * @version 1.0.0.0
 * @since 11/19/2024
 */


@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query("SELECT l FROM Libro l WHERE l.idioma = :language")
    List<Libro> librosPorIdioma(String language);

    @Query("SELECT a FROM Autor a WHERE a.nacimiento >= :rangeBeginning AND a.fallecimiento <= :rangeEnd")
    List<Autor> authorsByTimePeriod(int rangeBeginning, int rangeEnd);

    @Query("SELECT a FROM Autor a")
    List<Autor> findAllAuthors();
}
