package io.prueba.service.impl;

import io.prueba.dto.ProcessTokenResponse;
import io.prueba.dto.TokenRequest;
import io.prueba.feign.HttpClient;
import io.prueba.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class TokenServiceImpl implements TokenService {

    @Autowired
    private HttpClient httpClient;

    @Override
    public ProcessTokenResponse getToken() {
        TokenRequest tokenRequest = new TokenRequest();

        log.info("Solicitando token");
        String token = httpClient.getToken(tokenRequest).getToken();

        if(token.isEmpty()){
            log.info("El token recibido está vacío");
            throw new RuntimeException("Token vacío");
        }

        log.info("Token solicitado");
        log.info(token);

        ProcessTokenResponse processTokenResponse = new ProcessTokenResponse();
        processTokenResponse.setToken(token);
        processTokenResponse.setTimestamp(LocalDateTime.now().toString());

        log.info("Devolviendo respuesta");

        return processTokenResponse;
    }
}
