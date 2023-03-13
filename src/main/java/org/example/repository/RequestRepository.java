package org.example.repository;

import org.example.dataBase.DataBase;
import org.example.dto.Book;
import org.example.dto.Request;
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
public class RequestRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Request> requestList() {
        String sql = String.format("Select * from requests");
        List<Request> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Request.class));
        return list;
    }

    public void addRequest(Request request) {
        String sql = "insert into requests(student_id,bookTitle) values ('%s','%s');";
        sql = String.format(sql, request.getStudent_id(), request.getBookTitle());
        jdbcTemplate.update(sql);
    }
}
