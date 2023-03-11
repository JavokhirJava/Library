package org.example.repository;

import org.example.dataBase.DataBase;
import org.example.dto.Book;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class RequestRepository {
    public void addRequest(Integer studentId,String title){
        Connection connection = null;
        try {
            connection= DataBase.getConnection();
            String sql ="insert into requests(student_id,bookTitle)"+
                    "values ('%s','%s');";
            sql=String.format(sql,studentId,title);
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
