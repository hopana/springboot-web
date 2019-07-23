package com.hp.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@SpringBootApplication
@PropertySource(value = {"classpath:httpclient.properties"})
@ComponentScan(basePackages = "com.hp.springboot")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
