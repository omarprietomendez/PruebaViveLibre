package io.prueba.controller;

import io.prueba.dto.ProcessTokenResponse;
import io.prueba.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TokenController {

    private TokenService tokenService;

    @Operation(summary = "Endpoint para obtener un token")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",
                    description = "Ok",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProcessTokenResponse.class))}),
            @ApiResponse(responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProcessTokenResponse.class))})
    })
    @GetMapping("/token")
    public ResponseEntity<ProcessTokenResponse> token(){

        try{
            ProcessTokenResponse processTokenResponse = tokenService.getToken();
            return ResponseEntity.ok(processTokenResponse);
        } catch (Exception e) {
            return (ResponseEntity<ProcessTokenResponse>) ResponseEntity.internalServerError();
        }
    }
}
