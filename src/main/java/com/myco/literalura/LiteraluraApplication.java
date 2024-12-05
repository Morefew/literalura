package com.myco.literalura;

import com.myco.literalura.repository.LibroRepository;
import com.myco.literalura.view.LiteraluraApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
    @Autowired
    private LibroRepository libroRepository;

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        LiteraluraApp literaluraApp = new LiteraluraApp(libroRepository);
        literaluraApp.menu();
    }
}
