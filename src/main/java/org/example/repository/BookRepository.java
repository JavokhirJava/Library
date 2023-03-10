package org.example.repository;

import org.example.dataBase.DataBase;
import org.example.dto.Book;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
@Repository
public class BookRepository {

    public LinkedList<Book> getBookList(){
        LinkedList<Book> bookList = new LinkedList<>();
        Connection connection =null;
        try {
            connection = DataBase.getConnection();
            Statement statement = connection.createStatement();
            String sql = String.format("Select * from books");
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublishYear(resultSet.getString("publishYear"));
                book.setAmount(resultSet.getInt("amount"));
                book.setVisible(resultSet.getBoolean("visible"));
                bookList.add(book);
            }
            connection.close();
            return bookList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteBook(Integer id){
        Connection connection = null;
        try {
            connection= DataBase.getConnection();
            String sql = String.format("update books set visible = false where id ='%s';",id);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void bookActivation(Integer id){
        Connection connection = null;
        try {
            connection= DataBase.getConnection();
            String sql = String.format("update books set visible = true where id ='%s';",id);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addBook(Book book){
        Connection connection = null;
        try {
            connection= DataBase.getConnection();
            String sql ="insert into books(title,author,publishyear,amount,visible)"+
                    "values ('%s','%s','%s','%s','%s');";
            sql=String.format(sql,book.getTitle(),book.getAuthor(),book.getPublishYear(),
                    book.getAmount(),true);
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
