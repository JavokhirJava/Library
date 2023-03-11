package org.example.repository;

import org.example.dataBase.DataBase;
import org.example.dto.Book;
import org.example.dto.Request;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

@Repository
public class RequestRepository {
    public LinkedList<Request> requestList(){
        LinkedList<Request> requests = new LinkedList<>();
        Connection connection =null;
        try {
            connection = DataBase.getConnection();
            Statement statement = connection.createStatement();
            String sql = String.format("Select * from requests");
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Request request = new Request();
                request.setId(resultSet.getInt("id"));
                request.setStudent_id(resultSet.getInt("student_id"));
                request.setBookTitle(resultSet.getString("bookTitle"));
                requests.add(request);
            }
            connection.close();
            return requests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addRequest(Request request){
        Connection connection = null;
        try {
            connection= DataBase.getConnection();
            String sql ="insert into requests(student_id,bookTitle)"+
                    "values ('%s','%s');";
            sql=String.format(sql,request.getStudent_id(),request.getBookTitle());
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
