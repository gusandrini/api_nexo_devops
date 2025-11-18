package br.com.nexo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@EnableJpaRepositories
@ComponentScan
@EntityScan
@SpringBootApplication
public class ApiNexoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiNexoApplication.class, args);
	}

}
