package org.example.config;

import org.example.dataBase.InitDataBase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example")
public class Config {
//    @Bean
//    public InitDataBase initDataBase(){
//        InitDataBase initDataBase = new InitDataBase();
//        return initDataBase;
//    }
}
