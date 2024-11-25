package com.myco.literalura.view;

import com.myco.literalura.repository.BookRepository;
import com.myco.literalura.service.ConnectionToAPI;
import com.myco.literalura.service.DtoMapper;

import java.io.IOException;
import java.util.InputMismatchException;
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
    private Scanner scanner = new Scanner(System.in);
    private ConnectionToAPI conectionToAPI = new ConnectionToAPI();
    private final String URLBASE = "https://gutendex.com/books/?search=";
    private DtoMapper mapper = new DtoMapper();
    private BookRepository repository;

    public LiteraluraApp(BookRepository repository) {
        this.repository = repository;
    }


    public void menu() {
        int opcion = -1;
        while (opcion != 0) {
            var menu = """
                    
                    1 - Buscar Libro por Título
                    
                    0 - Salir
                    
                    """;
            System.out.println(menu);
            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                opcion = -1;
            }
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    getBookTitle();
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
     * Método para buscar un titulo de libro en la API de Guttendex
     * Si el título tiene algún ejemplar, se muestra el id, título, el autor y cantidad de descargas
     * Si no se encuentra ningún libro con ese título, se muestra un mensaje de error
     *
     * @param bookTitle
     * @return void
     * @throws IOException              Si ocurre algún error al leer el JSON
     * @throws JsonProcessingException  Si ocurre algún error al parsear el JSON
     * @throws JsonMappingException     Si ocurre algún error al mapear el JSON a un objeto
     * @throws RuntimeException        Si ocurre algún error al leer el JSON como un objeto
     * @throws NullPointerException     Si el título del libro es nulo
     * @throws IllegalArgumentException Si el título del libro es vacío
     */

    public void getBookTitle() {
        System.out.println("Escribe el título del libro que deseas buscar");
        var bookTitle = scanner.nextLine();
        var json = conectionToAPI.fetchData(URLBASE + bookTitle.replace(" ", "%20"));

        // Revisar si el JSON retorna sin resultados
        // mappar el JSON a un objeto JsonNode para leerlo como un objeto
        JsonNode rootNode = mapper.mapToJsonNode(json);

        // asignar el nodo "results" a un objeto JsonNode
        JsonNode results = rootNode.get("results");

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
            return;
        }

        // Imprimir los títulos de los libros encontrados
        int[] sampleIds = new int[results.size()];
        System.out.println("Libros Encontrados");
        for (int i = 0; i < sampleIds.length; i++) {
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
                    results.get(i).get("title").asText("titulo"),
                    results.get(i).get("authors").get(0),
                    results.get(i).get("languages").get(0).asText("idioma"),
                    results.get(i).get("download_count").asInt()
            ));

            sampleIds[i] = results.get(i).get("id").asInt();
        }

        // TODO: Funcionalidad que utiliza el id del libro seleccionado para buscar en la API sus datos
        // Si el usuario ingresó un título válido, se muestra la lista de libros con ese título
        // después de obtener los datos de los libros con el mismo título, el usuario puede elegir un libro usando el
        // ID y obtener los datos del libro seleccionado.

        System.out.println("Selecciona el Item No. del libro que deseas guardar");
        System.out.println("Total de Items: " + sampleIds.length);
        int libroSeleccionadoId = scanner.nextInt();
        if (libroSeleccionadoId > sampleIds.length) {
            System.out.println("El ID introducido no existe");
            return;
        }
        JsonNode libro = rootNode.get("results").get(libroSeleccionadoId);
        System.out.println(libro);




        // TODO: Parsear JSON a un objeto BookResult
        //  bookResult = mapper.mapToDto(json, BookResult.class);
        //  System.out.println("Libros Encontrados: " + bookResult.getResults());


//        return mapper.getObjectData(json, BookDTO.class);
    }

}
