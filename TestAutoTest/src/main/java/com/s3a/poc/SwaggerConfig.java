package com.s3a.poc;

import java.util.Collections;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.ant("/admin/**"))       
//          .paths(PathSelectors.any())
          .build()
          .apiInfo(getApiInfo())    
//          .genericModelSubstitutes(Optional.class)
          ; // add this              
        

//        .useDefaultResponseMessages(false)
//        .groupName("sample-api")
//        .apiInfo(apiInfo())
//        .select()
//        .paths(regex("/api.*"))
//        .build()
//        .genericModelSubstitutes(Optional.class); // add this
    }
    
    
    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "TITLE",
                "DESCIPRION",
                "VERSION",
                "TERMS OF SERVICE URL",
                new Contact("NAME","URL","EMAIL"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }
}