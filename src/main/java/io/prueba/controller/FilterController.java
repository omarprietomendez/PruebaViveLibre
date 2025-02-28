package io.prueba.controller;

import io.prueba.dto.ResponseWrapper;
import io.prueba.service.FilterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FilterController {

    private FilterService filterService;

    @GetMapping("/filter")
    public ResponseEntity<ResponseWrapper> filter(){
        filterService.process();
        return null;
    }

}
