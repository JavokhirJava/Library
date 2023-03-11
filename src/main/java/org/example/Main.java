package org.example;

import org.example.config.Config;
import org.example.controller.AuthController;
import org.example.dataBase.DataBase;
import org.example.dataBase.InitDataBase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) { 
        DataBase.initTable();
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        InitDataBase initDataBase = (InitDataBase) context.getBean("initDataBase");
//        initDataBase.adminInit();
        AuthController authController = (AuthController) context.getBean("authController");
        authController.start();
    }
}