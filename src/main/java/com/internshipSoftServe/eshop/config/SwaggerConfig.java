package com.internshipSoftServe.eshop.config;

import com.internshipSoftServe.eshop.controller.ArticlesController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.Entity;
import java.util.HashMap;
import java.util.Map;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@PropertySource("classpath:swagger.properties")
@ComponentScan(basePackageClasses = ArticlesController.class)
@Configuration
public class SwaggerConfig {
    private static final String SWAGGER_API_VERSION = "1,0";
    private static final String LICENSE_TEXT = "Licensed by Internship2019";
    private static final String TITLE = "E-Shop rest api";
    private static final String DESCRIPTION = "RestFULL API for products of its categories and articles ";

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .license(LICENSE_TEXT)
                .version(SWAGGER_API_VERSION)
                .build();
    }

    Map<String, Object> container = new HashMap<>();

    {
        container.put("Docket", new Docket(DocumentationType.SWAGGER_2));

        Object docket = container.get("Docket");
    }

    Object docket = container.get("Docket");

    @Bean
    public Docket productsApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("localhost:8090"))
                .paths(regex("/api/shop.*"))
                .build()
                .apiInfo(apiInfo());
    }
}
