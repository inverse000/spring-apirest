package com.example.obrestdatajpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Collections;

/**
 * Configuracion Swagger para la generacion de documentacion de la API REST
 * http://localhost:8080/swagger-ui/
 */
//@Configuration
public class SwaggerConfig {

//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiDetalles())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiDetalles(){
//        return new ApiInfo("Spring Boot Book API REST",
//                "Api rest docs",
//                "1.0",
//                "http://www.google.com",
//                new Contact("Jean", "http://www.google.com", "jean@gmail.com"),
//                "MIT",
//                "http://www.google.com",
//                Collections.emptyList());
//    }
}
