package br.com.nexo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApiNexoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiNexoApplication.class, args);
	}

}
