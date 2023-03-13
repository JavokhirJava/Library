package org.example.service;


import org.example.dto.Book;
import org.example.dto.Request;
import org.example.dto.StudentBook;
import org.example.dto.User;
import org.example.repository.BookRepository;
import org.example.repository.RequestRepository;
import org.example.repository.TakenBooksRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
public class AdminService {
    @Autowired
    TakenBooksRepository takenBooksRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    RequestRepository requestRepository;

    public void takenBooks() {
        List<StudentBook> studentBooks = takenBooksRepository.takenBooksAdmin();
        if (studentBooks.isEmpty()){
            System.out.println("All Books in Library");
            return;
        }
        for (StudentBook studentBook : studentBooks){
            Book book = bookRepository.getBookById(studentBook.getBook_id());
            User user = userRepository.getById(studentBook.getStudent_id());
            System.out.println("OrderNum-"+studentBook.getId()+" StudentName-"+user.getName()+" StudentPhone-"+user.getPhone()+
                    " BookTitle-"+book.getTitle()+" TakenDate-"+studentBook.getCreatedDate());
        }
    }

    public void takeHistory() {
        List<StudentBook> studentBookList= takenBooksRepository.takeListForAdmin();
        for (StudentBook studentBook : studentBookList){
            Book book = bookRepository.getBookById(studentBook.getBook_id());
            User user = userRepository.getById(studentBook.getStudent_id());
            System.out.println("OrderNum-"+studentBook.getId()+" StudentName-"+user.getName()+" StudentPhone-"+user.getPhone()+
                    " BookTitle-"+book.getTitle()+" BookAuthor-"+book.getAuthor()+" BookStatus-"+studentBook.getStatus()+
                    " TakenDate-"+studentBook.getCreatedDate());
        }
    }

    public void requests() {
        requestRepository.requestList().forEach(System.out::println);
    }
}
