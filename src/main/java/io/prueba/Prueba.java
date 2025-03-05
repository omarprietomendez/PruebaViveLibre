package io.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Prueba {

	public static void main(String[] args) {
		SpringApplication.run(Prueba.class, args);
	}

}
