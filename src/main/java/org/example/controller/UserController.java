package org.example.controller;

import org.example.container.ComponentContainer;
import org.example.repository.BookRepository;
import org.example.repository.TakenBooksRepository;
import org.example.service.NumScanner;
import org.example.service.StringScanner;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class UserController {
    @Autowired
    NumScanner numScanner;
    @Autowired
    StringScanner stringScanner;
    @Autowired
    ComponentContainer container;
    @Autowired
    UserService userService;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    TakenBooksRepository takenBooksRepository;

    public void start() {
        boolean b = true;
        while (b) {
            switch (menu()) {
                case 1 -> bookList();
                case 2 -> takeBook();
                case 3 -> takenBooks();
                case 4 -> returnBooks();
                case 5 -> history();
                case 6 -> orderBook();
                default -> b = false;
            }
        }
    }

    private void orderBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the book name :");
        String title = scanner.nextLine();
        userService.orderBook(title);
    }

    private void history() {
        userService.history();
    }

    private void returnBooks() {
        takenBooksRepository.takenBooks(ComponentContainer.user.getId()).forEach(System.out::println);
        System.out.println("Select book id which you want to return");
        Integer id = numScanner.getNumScanner().nextInt();
        userService.returnBook(id);
    }

    private void takenBooks() {
        userService.takenBooks();
    }

    private void takeBook() {
        bookRepository.getBookList().forEach(System.out::println);
        System.out.println("Enter Book id:");
        Integer id = numScanner.getNumScanner().nextInt();
        System.out.println("Enter duration of taking book");
        Integer duration = numScanner.getNumScanner().nextInt();
        userService.takeBook(id,duration);
    }
    private void bookList() {
        userService.booklist();
    }
    private Integer menu() {
        System.out.println("1.Book List");
        System.out.println("2.Take a Book");
        System.out.println("3.Taken Books");
        System.out.println("4.Return Book");
        System.out.println("5.History");
        System.out.println("6.Order Book");
        return numScanner.getNumScanner().nextInt();
    }
}
