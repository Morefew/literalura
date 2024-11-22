package com.myco.literalura.repository;

import com.myco.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * project literalura
 *
 * @author User
 * 1:06 PM 11/19/2024 2024
 * @Version 1.0.0.0
 * @since 11/19/2024
 */
public interface BookRepository extends JpaRepository<Book, Long> {

}
