package org.example.controller;

import org.example.dto.Book;
import org.example.service.BookService;
import org.example.service.NumScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class BookController {
    @Autowired
    private NumScanner numScanner;
    @Autowired
    private BookService bookService;
    public void start() {
        boolean b = true;
        while (b){
            switch (menu()){
                case 1 -> bookList();
                case 2 -> addBook();
                case 3 -> deleteBook();
                case 4 -> activateBook();
                default -> b=false;
            }
        }
    }

    private void activateBook() {
        System.out.println("Please enter book id ");
        Integer id = numScanner.getNumScanner().nextInt();
        bookService.activateBook(id);
    }

    private void deleteBook() {
        System.out.println("Please enter book id ");
        Integer id = numScanner.getNumScanner().nextInt();
        bookService.deleteBook(id);
    }

    private void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter title:");
        String title = scanner.nextLine();
        System.out.println("Enter author:");
        String author = scanner.nextLine();
        System.out.println("Enter publishedYear:");
        String publishedYear = scanner.nextLine();
        System.out.println("Enter amount");
        Integer amount = numScanner.getNumScanner().nextInt();

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setAmount(amount);
        book.setPublishYear(publishedYear);
        bookService.addBook(book);
    }

    private void bookList() {
        bookService.bookList();
    }

    private Integer menu() {
        System.out.println("1.Book List");
        System.out.println("2.Add Book");
        System.out.println("3.Delete Book");
        System.out.println("4.Book Activation");
        System.out.println("Choose :");
        return numScanner.getNumScanner().nextInt();
    }
}
