package io.prueba.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import io.prueba.dto.Author;
import io.prueba.dto.Book;
import io.prueba.service.FilterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
@Slf4j
public class FilterServiceImpl implements FilterService{

    private FilterService filterService;
    private ObjectMapper mapper = new ObjectMapper();
    private InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("books.json");
    private CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Book.class);
    List<Book> books =  mapper.readValue(in, collectionType);

    public FilterServiceImpl() throws IOException {
    }



    public void method1() {
        //Filtra los libros con más de 400 páginas y aquellos cuyo título contenga "Harry".
        List<Book> filteredList = books.stream()
                .filter(b -> b.getPages() > 400 && b.getTitle().contains("Harry"))
                .collect(Collectors.toList());

        log.info("Lista filtrada con los libros con más de 400 páginas y aquellos cuyo título contenga \"Harry\"");
        log.info(filteredList.toString());
    }

    public void method2() {
        //Obtén los libros escritos por "J.K. Rowling"
        List<Book> filteredList = books.stream()
                .filter(b -> b.getAuthor().getName().equals("J.K. Rowling"))
                .collect(Collectors.toList());

        log.info("Lista filtrada con los libros escritos por \"J.K. Rowling\"");
        log.info(filteredList.toString());
    }

    public void method3() {
        //Lista los títulos ordenados alfabéticamente
        List<Book> sorted = books.stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList());

        log.info("Lista ordenada alfabéticamente por título");
        log.info(sorted.toString());

        // cuenta cuántos libros ha escrito cada autor.
        Map<String, Long> result = books.stream().collect(groupingBy(book -> book.getAuthor().getName(), Collectors.counting()));

        log.info("Relación de libros escritos por autor");
        log.info(result.toString());
    }

    public void method4() {
        //Convierte publicationTimestamp a formato AAAA-MM-DD
        List<Book> formatteredList = books.stream()
                .filter(book -> book.getPublicationTimestamp() != null)
                .peek(book -> book.setPublicationTimestamp(String.valueOf(Instant.ofEpochMilli(Long.parseLong(book.getPublicationTimestamp()))).substring(0, 10)))
                .collect(Collectors.toList());

        log.info("Lista de libros con la fecha en formato AAAA-MM-DD");
        log.info(formatteredList.toString());
    }

    public void method5() {
        //Se organizan los libros
        List<Book> sorted = books.stream().sorted(Comparator.comparing(Book::getPages)).collect(Collectors.toList());

        //Se calcula el promedio de páginas
        Long median = books.stream().mapToLong(Book::getPages).sum() / (long) books.size();

        //Libro con menos páginas
        Book lessPages = sorted.get(0);

        //Libro con más páginas
        Book mostPages = sorted.get(sorted.size() - 1);

        log.info("Media de páginas");
        log.info(median.toString());
        log.info("Libro con menos páginas");
        log.info(lessPages.toString());
        log.info("Libro con más páginas");
        log.info(mostPages.toString());
    }

    public void method6() {
        //Añade un campo wordCount (250 palabras por página) y agrupa los libros por autor.
        Map<Author, List<Book>> map = books.stream()
                .peek(b -> b.setWordCount(b.getPages() * 250))
                .collect(groupingBy(Book::getAuthor));

        log.info("Libro agrupados por autor y con campo \"wordCount\"");
        log.info(map.toString());
    }

    public void method7() {
        //Recupera los libros sin fecha de publicación
        List<Book> booksNoTimestamp = books.stream()
                .filter(book -> book.getPublicationTimestamp() == null)
                .collect(Collectors.toList());

        log.info("Libros sin fecha de publicación");
        log.info(booksNoTimestamp.toString());

        //Encuentra si hay duplicados
        Boolean duplicated = books.stream()
                .collect(groupingBy(book -> book.getAuthor().getName(), Collectors.counting()))
                .entrySet()
                .stream()
                .anyMatch(entry -> entry.getValue() > 1);

        if (duplicated) {
            log.info("Hay duplicados");
        } else {
            log.info("No hay duplicados");
        }
    }

    public void method8() {
        //Ordena por fechas y saca el libro más reciente
        Book mostRecentBook = books.stream()
                .filter(book -> book.getPublicationTimestamp() != null)
                .peek(book -> book.setPublicationTimestamp(String.valueOf(Instant.ofEpochMilli(Long.parseLong(book.getPublicationTimestamp()))).substring(0, 10)))
                .sorted(Comparator.comparing(Book::getPublicationTimestamp))
                .collect(Collectors.toList())
                .get(0);

        log.info("Libro más reciente");
        log.info(mostRecentBook.toString());
    }

    public void method9() throws IOException {
        //Sacar el JSON
        Map<String, String> result = books.stream().collect(Collectors.toMap(Book::getTitle, b -> b.getAuthor().getName()));
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);

        log.info("Json de títulos y autores");
        log.info(json);

        //Saca el CSV (queda en la raiz del proyecto)
        File csvFile = new File("books.csv");
        try (PrintWriter pw = new PrintWriter(csvFile)) {
            books.stream().map(Book::toString).collect(Collectors.toList()).forEach(pw::println);
            log.info("CSV generado");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
