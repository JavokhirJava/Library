package org.example.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/library", "postgres", "Java2001");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
    public static void initTable(){

    }
    private static  void execute(String sql){
        try(Connection connection = getConnection()){
            Statement statement = connection.createStatement();
            statement.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
