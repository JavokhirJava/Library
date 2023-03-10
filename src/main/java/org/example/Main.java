package org.example;

import org.example.dataBase.DataBase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        DataBase.initTable();
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

    }
}