package com.myco.literalura.repository;

import com.myco.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * project literalura
 *
 * @author Lenny Gonzalez
 * 1:06 PM 11/19/2024 2024
 * @version 1.0.0.0
 * @since 11/19/2024
 */


@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.nacimiento >= :rangeBeginning AND a.fallecimiento <= :rangeEnd")
    List<Autor> authorsByTimePeriod(int rangeBeginning, int rangeEnd);
}
