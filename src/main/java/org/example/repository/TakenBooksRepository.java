package org.example.repository;

import org.example.dataBase.DataBase;
import org.example.dto.Book;
import org.example.dto.StudentBook;
import org.example.enums.BookStatus;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
@Repository
public class TakenBooksRepository {
    public LinkedList<StudentBook> takeByStudentId(Integer id) {
        LinkedList<StudentBook> studentBooks = new LinkedList<>();
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
            Statement statement = connection.createStatement();
            String sql = String.format("Select * from student_books where student_id ='%s';", id);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                StudentBook studentBook = new StudentBook();
                studentBook.setId(resultSet.getInt("id"));
                studentBook.setStudent_id(resultSet.getInt("student_id"));
                studentBook.setBook_id(resultSet.getInt("book_id"));
                studentBook.setCreatedDate(resultSet.getTimestamp("createdDate").toLocalDateTime());
                studentBook.setStatus(BookStatus.valueOf(resultSet.getString("status")));
                studentBook.setReturnedDate(resultSet.getTimestamp("returnedDate").toLocalDateTime());
                studentBook.setDuration(resultSet.getString("duration"));
                studentBooks.add(studentBook);
            }
            connection.close();
            return studentBooks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public StudentBook takeByStudentAndBookId(Integer student_id,Integer book_id) {
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
            Statement statement = connection.createStatement();
            String sql = String.format("Select * from student_books where student_id ='%s' and book_id = '%s';", student_id,book_id);
            ResultSet resultSet = statement.executeQuery(sql);
            StudentBook studentBook=null;
            if (resultSet.next()) {
                studentBook = new StudentBook();
                studentBook.setId(resultSet.getInt("id"));
                studentBook.setStudent_id(resultSet.getInt("student_id"));
                studentBook.setBook_id(resultSet.getInt("book_id"));
                studentBook.setCreatedDate(resultSet.getTimestamp("createdDate").toLocalDateTime());
                studentBook.setStatus(BookStatus.valueOf(resultSet.getString("status")));
                studentBook.setReturnedDate(resultSet.getTimestamp("returnedDate").toLocalDateTime());
                studentBook.setDuration(resultSet.getString("duration"));
            }
            connection.close();
            return studentBook;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void takeABook(StudentBook studentBook, Integer duration) {
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
            String sql = "insert into student_books(student_id,book_id,createdDate,status,returnedDate,duration)" +
                    "values ('%s','%s','%s','%s','%s','%s');";
            sql = String.format(sql, studentBook.getStudent_id(),studentBook.getBook_id(), LocalDateTime.now(), BookStatus.TAKEN,
                    LocalDateTime.of(LocalDateTime.now().toLocalDate().plusDays(duration), LocalTime.now()), duration);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void returnABook(StudentBook studentBook) {
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
            String sql = "insert into student_books(student_id,book_id,createdDate,status,returnedDate,duration)" +
                    "values ('%s','%s','%s','%s','%s','%s');";
            sql = String.format(sql, studentBook.getStudent_id(),studentBook.getBook_id(),LocalDateTime.now() , BookStatus.RETURNED,
                    studentBook.getReturnedDate(), studentBook.getDuration());
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            changeStatus(studentBook.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void changeStatus(Integer id){
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
            String sql = String.format("update student_books set status = 'RETURNED' where id ='%s';",id);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<StudentBook> takenBooks(Integer id) {
        LinkedList<StudentBook> studentBooks = new LinkedList<>();
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
            Statement statement = connection.createStatement();
            String sql = String.format("Select * from student_books where student_id ='%s' and status ='TAKEN';", id);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                StudentBook studentBook = new StudentBook();
                studentBook.setId(resultSet.getInt("id"));
                studentBook.setStudent_id(resultSet.getInt("student_id"));
                studentBook.setBook_id(resultSet.getInt("book_id"));
                studentBook.setCreatedDate(resultSet.getTimestamp("createdDate").toLocalDateTime());
                studentBook.setStatus(BookStatus.valueOf(resultSet.getString("status")));
                studentBook.setReturnedDate(resultSet.getTimestamp("returnedDate").toLocalDateTime());
                studentBook.setDuration(resultSet.getString("duration"));
                studentBooks.add(studentBook);
            }
            connection.close();
            return studentBooks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
