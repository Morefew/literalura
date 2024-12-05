package com.myco.literalura.view;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.myco.literalura.model.Autor;
import com.myco.literalura.model.Libro;
import com.myco.literalura.model.LibroDTO;
import com.myco.literalura.repository.LibroRepository;
import com.myco.literalura.service.ConnectionToAPI;
import com.myco.literalura.service.DtoMapper;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * project literalura
 *
 * @author Lenny Gonzalez
 * 12:39 PM 11/19/2024 2024
 * @version 1.0.0.0
 * @since 11/19/2024
 */
public class LiteraluraApp {
    private final String URLBASE = "https://gutendex.com/books/?search=";
    private Scanner scanner = new Scanner(System.in);
    private ConnectionToAPI conectionToAPI = new ConnectionToAPI();
    private DtoMapper mapper = new DtoMapper();
    private LibroRepository librosRepository;
    private JsonNode results;
    private List<Libro> libros;


    public LiteraluraApp(LibroRepository libroRepository) {
        this.librosRepository = libroRepository;
    }

    public void menu() {
        int option = -1;
        while (option != 0) {
            var menu = """
                    
                    1 - Buscar Libro por Título
                    2 - Lista de Libros Salvados
                    3 - Lista de autores
                    4 - Lista de autores vivos en determinado año
                    5 - Listar Libros por Idioma determinado
                    
                    0 - Salir
                    
                    """;
            System.out.println(menu);
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingresa un número válido.");
                scanner.nextLine(); // Clear buffer
                continue; // Skip to next iteration
            }
            scanner.nextLine();

            switch (option) {
                case 1:
                    searchBookTitle();
                    break;
                case 2:
                    showSavedBooks();
                    break;
                case 3:
                    showAllAuthors();
                    break;
                case 4:
                    authorsByTimePeriod();
                    break;
                case 5:
                    booksByLanguage();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("\n \u001B[031;1mOpción inválida \n \u001B[0m");
            }
        }

    }

    /**
     * Fetches a book title from the GutenDex API
     * If the title has any exemplars, it displays: libroId, title, author and download count of the book
     * If no book with that title is found, an error message is displayed
     *
     * @return void
     * @throws RuntimeException         If an error occurs while reading the JSON as an object
     * @throws NullPointerException     If the book title is null
     * @throws IllegalArgumentException If the book title is empty
     */

    private JsonNode getBookTitle(String bookTitle) {
        var json = conectionToAPI.fetchData(URLBASE + bookTitle.replace(" ", "%20"));
        // assigns the JSON in the API response to a JsonNode
        // so that it can be read as an object
        // The node we are interested in is "results"
        return mapper.mapToJsonNode(json).get("results");
    }


    /**
     * Search for a book title in the GutenDex API
     * Functionality that uses the libroId of the selected book to search for data on the API
     * If the user entered a valid title, the list of libros with that title is displayed
     * After obtaining the data of the libros with the same title, the user can choose a book using the
     * ID and obtain the data of the selected book.
     */

    public void searchBookTitle() {
        int[] booksIds;
        System.out.println("Escribe el título del libro que deseas buscar");
        var bookTitle = scanner.nextLine();
        results = getBookTitle(bookTitle);
        // Checks if the JSON returned has results or not
        if (results.isEmpty()) {
            int width = 30; // Total width of the line
            int padding = (width - bookTitle.length()) / 2;
            String centeredTitle = " ".repeat(padding) + bookTitle;
            System.out.println();
            System.out.println(
                    """
                            \u001b[40m
                            \u001B[31m     Libro con el título:
                            \u001B[33;1m %s\u001B[0m\u001b[40m
                            \u001B[31m No se encontró en el sistema
                            
                            \u001B[0m
                            """.formatted(centeredTitle));
        }

        // Print the titles of the books found
        booksIds = new int[results.size()];
        System.out.println("Libros Encontrados");
        for (int i = 0; i < booksIds.length; i++) {
            System.out.println("""
                    \u001B[40m\u001B[34mItem No.: %d
                    \u001B[0m\u001B[34mID:\u001B[0m %d
                    \u001B[34mTítulo:\u001B[0m %s
                    \u001B[34mAutor:\u001B[0m %s
                    \u001B[34mIdioma:\u001B[0m %s
                    \u001B[34mCant. de Descargas:\u001B[0m %d
                    \u001B[40m
                    \u001B[0m
                    """.formatted(
                    i,
                    results.get(i).get("id").asInt(),
                    results.get(i).get("title").asText("titulo"), results.get(i).get("authors").get(0).get("name").asText(),
                    results.get(i).get("languages").get(0).asText("idioma"),
                    results.get(i).get("download_count").asInt()
            ));

            // Store the ID of the book found and make it available to the user
            // to select the book to save
            booksIds[i] = results.get(i).get("id").asInt();
        }

        System.out.println("Total de Items: " + booksIds.length);
        System.out.println("Selecciona el Item No. del libro que deseas guardar");
        int libroSeleccionadoId = scanner.nextInt();

        if (libroSeleccionadoId < 0 || libroSeleccionadoId >= booksIds.length) {
            System.out.println("El número ingresado no es válido.");
            return;
        }

        ArrayNode arrayNode = (ArrayNode) results;
        JsonNode arrayElement = arrayNode.get(libroSeleccionadoId);
        System.out.println("libro seleccionado: " + arrayElement);

        LibroDTO bookSelected = mapper.mapToDto(arrayElement.toString(), LibroDTO.class);
        Libro newBook = new Libro(bookSelected);
        System.out.println(newBook);

        // save book to database
        librosRepository.save(newBook);
        System.out.println("Libro guardado en la base de datos\n" + newBook);
    }

    /**
     * List libros saved in the database by the user
     */
    private void showSavedBooks() {
        libros = librosRepository.findAll();

        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }

    /**
     * 3 - Lists all autores
     */
    private void showAllAuthors() {
        List<Autor> autores = librosRepository.findAllAuthors();

        autores.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(System.out::println);
    }

    /**
     * 4 - Lists autores alive during a time period
     */
    private void authorsByTimePeriod() {
        System.out.println("Ingrese el año con el que INICIAR el rango ");
        var rangeBeginning = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el año con el que TERMINAR el rango ");
        var rangeEnd = scanner.nextInt();
        scanner.nextLine();

        List<Autor> authors = librosRepository.authorsByTimePeriod(rangeBeginning, rangeEnd);
        System.out.println("Autores Encontrados");
        authors.stream().forEach(System.out::println);
    }


    /**
     * 5 - Lists Libros by language
     */
    private void booksByLanguage(){
        System.out.println("Ingrese el idioma de los libros a buscar:");
        String idioma = scanner.nextLine();

        libros = librosRepository.librosPorIdioma(idioma);
        System.out.println("Libros encontrados");
        libros.stream().forEach(System.out::println);
    }
}
