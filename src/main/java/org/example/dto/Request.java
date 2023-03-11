package org.example.dto;

public class Request {
    private Integer id;
    private Integer student_id;
    private String bookTitle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", student_id=" + student_id +
                ", bookTitle='" + bookTitle + '\'' +
                '}';
    }
}
