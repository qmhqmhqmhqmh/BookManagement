package com.example.demo.api;

import com.example.demo.entity.Book;

import java.util.List;

public class ResponseBook {
    private boolean ok; // 修改字段名称
    private String message; // 修改字段名称
    private List<Book> data; // 修改字段名称

    // Constructors
    public ResponseBook() {}

    public ResponseBook(boolean ok, String message, List<Book> data) {
        this.ok = ok;
        this.message = message;
        this.data = data;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Book> getData() {
        return data;
    }

    public void setData(List<Book> data) {
        this.data = data;
    }
}
