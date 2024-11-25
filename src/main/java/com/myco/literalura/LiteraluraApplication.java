package com.myco.literalura;

import com.myco.literalura.repository.BookRepository;
import com.myco.literalura.view.LiteraluraApp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    private BookRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        LiteraluraApp literaluraApp = new LiteraluraApp(repository);
        literaluraApp.menu();
    }
}
