package io.prueba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String publicationTimestamp;
    private Long pages;
    private String summary;
    private Author author;
}
