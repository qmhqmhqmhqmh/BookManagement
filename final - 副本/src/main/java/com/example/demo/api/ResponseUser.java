
package com.example.demo.api;

import com.example.demo.entity.User;

import java.util.List;

public class ResponseUser {
    private boolean ok; // 修改字段名称
    private String message; // 修改字段名称
    private List<User> data; // 修改字段名称

    // Constructors
    public ResponseUser() {}

    public ResponseUser(boolean ok, String message, List<User> data) {
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

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}

