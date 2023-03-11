package org.example.controller;

import org.example.dto.User;
import org.example.service.AdminService;
import org.example.service.NumScanner;
import org.example.service.StringScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {
    @Autowired
    private NumScanner numScanner;
    @Autowired
    private StringScanner stringScanner;
    @Autowired
    private AdminService adminService;
    @Autowired
    private BookController bookController;
    @Autowired
    private StudentController studentController;
    public void start() {
        boolean b = true;
        while (b){
            switch (menu()){
                case 1 -> bookMenu();
                case 2 -> studentMenu();
                case 3 -> takenBooks();
                case 4 -> takenHistory();
                default -> b=false;
            }
        }
    }

    private void studentMenu() {
        studentController.start();
    }

    private void bookMenu() {
        bookController.start();
    }

    private void takenHistory() {
        adminService.takeHistory();
    }

    private void takenBooks() {
     adminService.takenBooks();
    }

    private Integer menu() {
        System.out.println("1.Book Menu");
        System.out.println("2.Student Menu");
        System.out.println("3.Student Taken Book");
        System.out.println("4.BookTaken History");
        System.out.println("Choose :");
        return numScanner.getNumScanner().nextInt();
    }
}
