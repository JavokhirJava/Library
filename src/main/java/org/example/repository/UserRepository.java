package org.example.repository;

import org.example.dataBase.DataBase;
import org.example.dto.User;
import org.example.enums.Role;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserRepository {
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
}
