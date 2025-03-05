package io.prueba.feign;

import io.prueba.dto.TokenRequest;
import io.prueba.dto.TokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "client", url = "http://localhost:8080")
public interface HttpClient {

    @RequestMapping(method = RequestMethod.POST, value = "/token")
    TokenResponse getToken(@RequestBody TokenRequest tokenRequest);
}
