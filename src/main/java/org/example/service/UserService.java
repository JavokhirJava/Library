package org.example.service;

import org.example.container.ComponentContainer;
import org.example.dto.Book;
import org.example.dto.Request;
import org.example.dto.StudentBook;
import org.example.dto.User;
import org.example.repository.BookRepository;
import org.example.repository.RequestRepository;
import org.example.repository.TakenBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    TakenBooksRepository takenBooksRepository;
    @Autowired
    BookRepository bookRepository;

    public void booklist() {
        User user = ComponentContainer.user;
        if (takenBooksRepository.takeByStudentId(user.getId()).isEmpty()) {
            System.out.println("Your book list is empty!");
            return;
        }
        List<StudentBook> studentBooks = takenBooksRepository.takeByStudentId(user.getId());
        for (StudentBook studentBook : studentBooks){
            Book book = bookRepository.getBookById(studentBook.getBook_id());
            System.out.println("OrderNum-"+studentBook.getId()+" Title-"+book.getTitle()+" Author-"+book.getAuthor());
        }

    }

    public void takeBook(Integer id, Integer duration) {
        int count = 0;
        List<StudentBook> studentBookList = takenBooksRepository.takeByStudentId(ComponentContainer.user.getId());
        List<StudentBook> books = takenBooksRepository.takenBooks(ComponentContainer.user.getId());
        Book book = bookRepository.getBookById(id);
        for (StudentBook studentBook : studentBookList) {
            count++;
        }
        for (StudentBook studentBook : books){
            if(studentBook.getBook_id().equals(id)){
                System.out.println("You already have this book");
                return;
            }
        }
        if (count == 5) {
            System.out.println("Limit of taking books is 5 please return some books");
        } else if (book == null) {
            System.out.println("Book does not exist in library invalid ID");
        } else if (!book.getVisible()){
            System.out.println("This book has been deleted by the admin, you can not take this book!");
        }else if (book.getAmount() == 0) {
            System.out.println("Unfortunately, this book is no longer in the library, but you can order this book!");
        } else {
            StudentBook studentBook = new StudentBook();
            studentBook.setStudent_id(ComponentContainer.user.getId());
            studentBook.setBook_id(id);
            takenBooksRepository.takeABook(studentBook, duration);
            bookRepository.bookTaken(id);
            System.out.println("Book added to your book list");
        }
    }

    public void takenBooks() {
        User user = ComponentContainer.user;
        List<StudentBook> takenBooks = takenBooksRepository.takenBooks(user.getId());
        if (takenBooks.isEmpty()) {
            System.out.println("You didn't get  any book yet");
            return;
        }
        for (StudentBook studentBook : takenBooks) {
            Book book = bookRepository.getBookById(studentBook.getBook_id());
            System.out.println("OrderNum- " + studentBook.getId() + " BookTitle- " + book.getTitle() + " Book Author- " +
                    book.getAuthor() + " TakenTime- " + studentBook.getCreatedDate());
        }
    }

    public void returnBook(Integer id) {
        User user = ComponentContainer.user;
        List<StudentBook> takenBooks = takenBooksRepository.takenBooks(user.getId());
        Boolean b = true;
        for (StudentBook studentBook : takenBooks) {
            if (!Objects.equals(studentBook.getBook_id(), id)) {
                b = false;
            } else {
                b = true;
                break;
            }
        }
        if (!b) {
            System.out.println("You didn't get this book");
            return;
        }
        StudentBook studentBook = takenBooksRepository.takeByStudentAndBookId(user.getId(), id);
        takenBooksRepository.returnABook(studentBook);
        bookRepository.bookReturn(id);
        System.out.println(bookRepository.getBookById(id).getTitle() + "-book is returned successfully");
    }

    public void history() {
        List<StudentBook> studentBooks = takenBooksRepository.takeByStudentId(ComponentContainer.user.getId());
        for (StudentBook studentBook : studentBooks){
            Book book = bookRepository.getBookById(studentBook.getBook_id());
            System.out.println("OrderNum-"+studentBook.getId()+" BookTitle-"+book.getTitle()+" BookAuthor-"+book.getAuthor()+
                    " Status-"+studentBook.getStatus()+" TakenDate-"+studentBook.getCreatedDate()+" returnedDate-"+studentBook.getReturnedDate());
        }
    }

    public void orderBook(String  title) {
        Book book = bookRepository.getBookByName(title);
        if (book !=null){
            System.out.println("this book is exist in our library");
        }else {
            Request request = new Request();
            request.setStudent_id(ComponentContainer.user.getId());
            request.setBookTitle(title);
            requestRepository.addRequest(request);
            System.out.println("Your request is successfully taken");
        }
    }
}
