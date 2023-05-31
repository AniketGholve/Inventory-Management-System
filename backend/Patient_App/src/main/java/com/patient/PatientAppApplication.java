package com.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
@EnableScheduling
public class PatientAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientAppApplication.class, args);
	}
	
	
	@Bean
    OpenAPI Demo() {
        return new OpenAPI()
                .info(new Info().title("Inventory Management")
                .description("Inventory Management")
                .version("v0.0.1")
                .termsOfService("Terms of Service")
                );
    }
	
	@Bean 
	public RestTemplate restTemplate()
	{ return new RestTemplate(); }


}
