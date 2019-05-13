package com.dm_daddy.first_edition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;

@SpringBootConfiguration
@EnableAutoConfiguration
public class FirstEditionTestsConfiguration {
    @Primary
    public static void main(String[] args){
        SpringApplication.run(FirstEditionApplication.class, args);
    }
}
