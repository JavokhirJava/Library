package org.example.service;

import org.example.dto.Book;
import org.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    public void activateBook(Integer id) {
        bookRepository.bookActivation(id);
        System.out.println("Book activated successfully");
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteBook(id);
        System.out.println("Book deleted successfully");
    }

    public void addBook(Book book) {
        bookRepository.addBook(book);
        System.out.println("Book added successfully");
    }

    public void bookList() {
        bookRepository.getBookList().stream().forEach(System.out::println);
    }
}
