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
    private Long wordCount;

    @Override
    public String toString(){
        return String.valueOf(this.id) + "," + this.getTitle() + "," + this.getAuthor().getName() + "," + String.valueOf(this.pages);
    }
}
