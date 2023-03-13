package org.example.repository;

import org.example.dataBase.DataBase;
import org.example.dto.Book;
import org.example.dto.StudentBook;
import org.example.enums.BookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Repository
public class TakenBooksRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<StudentBook> takeByStudentId(Integer id) {
        String sql = String.format("Select * from student_books where student_id ='%s';", id);
        List<StudentBook> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(StudentBook.class));
        return list;
    }

    public List<StudentBook> takeListForAdmin() {
        String sql = String.format("Select * from student_books;");
        List<StudentBook> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(StudentBook.class));
        return list;
    }

    public StudentBook takeByStudentAndBookId(Integer student_id, Integer book_id) {
        String sql = String.format("Select * from student_books where student_id ='%s' and book_id = '%s';", student_id, book_id);
        List<StudentBook> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(StudentBook.class));
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public void takeABook(StudentBook studentBook, Integer duration) {
        String sql = "insert into student_books(student_id,book_id,createdDate,status,returnedDate,duration)" +
                "values ('%s','%s','%s','%s','%s','%s');";
        sql = String.format(sql, studentBook.getStudent_id(), studentBook.getBook_id(), LocalDateTime.now(), BookStatus.TAKEN,
                LocalDateTime.of(LocalDateTime.now().toLocalDate().plusDays(duration), LocalTime.now()), duration);
        jdbcTemplate.update(sql);
    }


    public void returnABook(StudentBook studentBook) {
        String sql = "insert into student_books(student_id,book_id,createdDate,status,returnedDate,duration)" +
                "values ('%s','%s','%s','%s','%s','%s');";
        sql = String.format(sql, studentBook.getStudent_id(), studentBook.getBook_id(), LocalDateTime.now(), BookStatus.RETURNED,
                studentBook.getReturnedDate(), studentBook.getDuration());
        changeStatus(studentBook.getId());
        jdbcTemplate.update(sql);
    }

    public void changeStatus(Integer id) {
        String sql = String.format("update student_books set status = 'RETURNED' where id ='%s';", id);
        jdbcTemplate.update(sql);
    }

    public List<StudentBook> takenBooks(Integer id) {
            String sql = String.format("Select * from student_books where student_id ='%s' and status ='TAKEN';", id);
            List<StudentBook> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(StudentBook.class));
            return list;
    }

    public List<StudentBook> takenBooksAdmin() {
            String sql = String.format("Select * from student_books where status ='TAKEN';");
            List<StudentBook> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(StudentBook.class));
            return list;
    }
}
