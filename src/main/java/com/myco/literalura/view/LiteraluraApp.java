package com.myco.literalura.view;

import com.myco.literalura.repository.BookRepository;
import com.myco.literalura.service.ConnectionToAPI;
import com.myco.literalura.service.DtoMapper;

import java.util.Scanner;

/**
 * project literalura
 *
 * @author User
 * 12:39 PM 11/19/2024 2024
 * @version 1.0.0.0
 * @since 11/19/2024
 */
public class LiteraluraApp {
    private Scanner scanner = new Scanner(System.in);
    private ConnectionToAPI conectionToAPI = new ConnectionToAPI();
    private final String URLBASE = "https://gutendex.com/books/";
    private DtoMapper mapper = new DtoMapper();
    private BookRepository repository;

    public LiteraluraApp(BookRepository repository) {
        this.repository = repository;
    }

    public void run() {

        System.out.println(conectionToAPI.fetchData(URLBASE));

    }
}
