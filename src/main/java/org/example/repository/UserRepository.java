package org.example.repository;

import org.example.dataBase.DataBase;
import org.example.dto.User;
import org.example.enums.Role;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;

@Repository
public class UserRepository {
    public LinkedList<User> getStudentList(){
        LinkedList<User> users = new LinkedList<>();
        Connection connection =null;
        try {
            connection = DataBase.getConnection();
            Statement statement = connection.createStatement();
            String sql = String.format("Select * from users where role ='STUDENT'");
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setPhone(resultSet.getString("phone"));
                user.setCreatedDate(resultSet.getTimestamp("createdDate").toLocalDateTime().toLocalDate());
                user.setVisible(resultSet.getBoolean("visible"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                users.add(user);
            }
            connection.close();
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public User getUserByPhone(String phone){
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
            Statement statement = connection.createStatement();
            String sql = String.format("Select * from users where phone ='%s';",phone);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setPhone(resultSet.getString("phone"));
                user.setCreatedDate(resultSet.getTimestamp("createdDate").toLocalDateTime().toLocalDate());
                user.setVisible(resultSet.getBoolean("visible"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                return user;
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public User getById(Integer id){
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
            Statement statement = connection.createStatement();
            String sql = String.format("Select * from users where id ='%s';",id);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setPhone(resultSet.getString("phone"));
                user.setCreatedDate(resultSet.getTimestamp("createdDate").toLocalDateTime().toLocalDate());
                user.setVisible(resultSet.getBoolean("visible"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                return user;
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void deleteStudent(Integer id){
        Connection connection = null;
        try {
            connection= DataBase.getConnection();
            String sql = String.format("update users set visible = false where id ='%s';",id);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void studentActivation(Integer id){
        Connection connection = null;
        try {
            connection= DataBase.getConnection();
            String sql = String.format("update users set visible = true where id ='%s';",id);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveUser(User user){
        Connection connection = null;
        try {
            connection= DataBase.getConnection();
            String sql ="insert into users(name,surname,phone,createdDate,visible,role)"+
                    "values ('%s','%s','%s','%s','%s','%s');";
            sql=String.format(sql,user.getName(),user.getSurname(),user.getPhone(),user.getCreatedDate(),
                    user.getVisible(),user.getRole());
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException e) {
             e.printStackTrace();
            }
        }
    }
    public void addStudent(User user){
        Connection connection = null;
        try {
            connection= DataBase.getConnection();
            String sql ="insert into users(name,surname,phone,createdDate,visible,role)"+
                    "values ('%s','%s','%s','%s','%s','%s');";
            sql=String.format(sql,user.getName(),user.getSurname(),user.getPhone(), LocalDate.now(),
                    true,Role.STUDENT);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException e) {
             e.printStackTrace();
            }
        }
    }
}
