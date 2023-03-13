package org.example.repository;

import org.example.dataBase.DataBase;
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
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> getStudentList() {
        String sql = String.format("Select * from users where role ='STUDENT'");
        List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        return list;
    }

    public User getUserByPhone(String phone) {
        String sql = String.format("Select * from users where phone ='%s';", phone);
        List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public User getById(Integer id) {
        String sql = String.format("Select * from users where id ='%s';", id);
        List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public void deleteStudent(Integer id) {
        String sql = String.format("update users set visible = false where id ='%s';", id);
        jdbcTemplate.update(sql);
    }

    public void studentActivation(Integer id) {
        String sql = String.format("update users set visible = true where id ='%s';", id);
        jdbcTemplate.update(sql);
    }

    public void saveUser(User user) {
        String sql = "insert into users(name,surname,phone,createdDate,visible,role)" +
                "values ('%s','%s','%s','%s','%s','%s');";
        sql = String.format(sql, user.getName(), user.getSurname(), user.getPhone(), user.getCreatedDate(),
                user.getVisible(), user.getRole());
        jdbcTemplate.update(sql);
    }

    public void addStudent(User user) {
        String sql = "insert into users(name,surname,phone,createdDate,visible,role)" +
                "values ('%s','%s','%s','%s','%s','%s');";
        sql = String.format(sql, user.getName(), user.getSurname(), user.getPhone(), LocalDate.now(),
                true, Role.STUDENT);
        jdbcTemplate.update(sql);
    }
}