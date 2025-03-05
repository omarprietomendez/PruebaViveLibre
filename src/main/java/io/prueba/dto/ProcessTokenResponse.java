package io.prueba.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProcessTokenResponse {
    private String token;
    private String timestamp;
}
