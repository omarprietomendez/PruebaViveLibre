package io.prueba.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import io.prueba.dto.Book;
import io.prueba.service.FilterService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FilterServiceImpl implements FilterService{

    private FilterService filterService;
    private ObjectMapper mapper = new ObjectMapper();
    private InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("books.json");
    private CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Book.class);
    private List<Book> books =  mapper.readValue(in, collectionType);

    public FilterServiceImpl() throws IOException {
    }

    @Override
    public void process() {
        try{
            
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
