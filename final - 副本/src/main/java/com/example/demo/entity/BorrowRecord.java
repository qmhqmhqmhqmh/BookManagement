package com.example.demo.entity;

import java.time.LocalDateTime;

public class BorrowRecord {
    private int id;
    private String user_name;
    private String book_name;
    private LocalDateTime borrow_date;
    private LocalDateTime return_date;
    private String status;

    public BorrowRecord() {

    }

    public BorrowRecord(int id, String user_name, String book_name, LocalDateTime borrowDate, LocalDateTime returnDate, String status) {
        this.id = id;
        this.user_name = user_name;
        this.book_name = book_name;
        this.borrow_date = borrowDate;
        this.return_date = returnDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getuser_name() {
        return user_name;
    }

    public void setuser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getbook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public LocalDateTime getBorrowDate() {
        return borrow_date;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrow_date = borrowDate;
    }

    public LocalDateTime getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDateTime return_date) {
        this.return_date = return_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BorrowRecord{" +
                "id=" + id +
                ", user_name=" + user_name +
                ", book_name=" + book_name +
                ", borrowDate=" + borrow_date +
                ", return_date=" + return_date +
                ", status='" + status + '\'' +
                '}';
    }
}
