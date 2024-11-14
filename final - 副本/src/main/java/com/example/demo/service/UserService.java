package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Logger;

@Service
public class UserService {

    @Autowired
        private UserMapper userMapper;

        public User findByUsername(String username) {
            return userMapper.findByUsername(username);
        }

        public void register(User user) {
            userMapper.insertUser(user);
        }


        public User findById(int id) {
            return userMapper.findById(id);
        }


        public List<User> findAll() {
            return userMapper.findAll();
        }


        public void updateUser(User user) {
            userMapper.updateUser(user);
        }


        public boolean deleteUser(int id) {
            return userMapper.deleteUser(id);
        }


        public boolean existsByUsername(String username) {
            return userMapper.existsByUsername(username);
        }

        public void setUserMapper(UserMapper userMapper) {
            this.userMapper = userMapper;
        }



        public void logout(HttpServletRequest request) {
            HttpSession session = request.getSession();
            session.removeAttribute("user");
            session.invalidate();//释放会话
        }

        public List<User> searchUsers(String query) {
            return userMapper.searchUsers(query);
        }
    }



