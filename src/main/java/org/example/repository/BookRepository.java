package org.example.repository;

import org.example.dataBase.DataBase;
import org.example.dto.Book;
import org.example.dto.User;
import org.example.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

@Repository
public class BookRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Book> getBookList() {
        String sql = "SELECT * FROM books";
        List<Book> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        return list;
    }

    public Book getBookById(Integer id) {
        String sql = "SELECT * FROM books Where id =" + id;
        List<Book> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public Book getBookByName(String title) {
        String sql = String.format("SELECT * FROM books Where title ='%s'", title);
        List<Book> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public void bookTaken(Integer id) {
        Book book = getBookById(id);
        Integer amount = book.getAmount() - 1;
        String sql = String.format("update books set amount = '%s' where id ='%s';", amount, id);
        jdbcTemplate.update(sql);
    }

    public void bookReturn(Integer id) {
        Book book = getBookById(id);
        Integer amount = book.getAmount() + 1;
        String sql = String.format("update books set amount = '%s' where id ='%s';", amount, id);
        jdbcTemplate.update(sql);
    }

    public void deleteBook(Integer id) {
        String sql = String.format("update books set visible = false where id ='%s';", id);
        jdbcTemplate.update(sql);
    }

    public void bookActivation(Integer id) {
        String sql = String.format("update books set visible = true where id ='%s';", id);
        jdbcTemplate.update(sql);
    }

    public void addBook(Book book) {
        String sql = "insert into books(title,author,publishyear,amount,visible)" +
                "values ('%s','%s','%s','%s','%s');";
        sql = String.format(sql, book.getTitle(), book.getAuthor(), book.getPublishYear(),
                book.getAmount(), true);
        jdbcTemplate.update(sql);
    }
}
