package org.example.dto;

import org.example.enums.BookStatus;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Table
public class StudentBook {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private Integer student_id;
    private Integer book_id;
    private LocalDateTime createdDate;
    private BookStatus status;
    private LocalDateTime returnedDate;
    private String duration;

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

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public LocalDateTime getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDateTime returnedDate) {
        this.returnedDate = returnedDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", student_id=" + student_id +
                ", book_id=" + book_id +
                ", createdDate=" + createdDate +
                ", status=" + status +
                ", returnedDate=" + returnedDate +
                ", duration=" + duration ;
    }
}
