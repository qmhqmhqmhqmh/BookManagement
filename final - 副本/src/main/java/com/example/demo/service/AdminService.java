package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public Admin findByUsername(String username) {
        return adminMapper.findByUsername(username);
    }

    public void register(Admin admin) {
        adminMapper.insertAdmin(admin);
    }
}
