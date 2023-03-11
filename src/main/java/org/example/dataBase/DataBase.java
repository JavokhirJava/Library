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
      String users = "create table if not exists users(" +
              "id serial primary key," +
              "name varchar(25) not null," +
              "surname varchar(25) not null," +
              "phone varchar(13) not null," +
              "createdDate timestamp not null," +
              "role varchar(25) not null," +
              "visible boolean not null" +
              ")";
      String books ="create table if not exists books(" +
              "id serial primary key," +
              "title varchar(25) not null," +
              "author varchar(25) not null," +
              "publishYear varchar(4) not null," +
              "amount integer not null," +
              "visible boolean not null" +
              ")";
      String studentBook = "create table if not exists student_books(" +
              "id serial primary key," +
              "student_id integer not null," +
              "book_id integer not null," +
              "createdDate timestamp ," +
              "status varchar(25) not null," +
              "returnedDate timestamp ," +
              "duration integer not null," +
              "foreign key(book_id) references books(id)," +
              "foreign key(student_id) references users(id)" +
              ")";
      String request = "create table if not exists requests(" +
              " id serial primary key," +
              " student_id integer not null," +
              " bookTitle varchar(25) not null," +
              " foreign key(student_id) references users(id)" +
              ")";
      execute(request);
      execute(users);
      execute(books);
      execute(studentBook);
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
