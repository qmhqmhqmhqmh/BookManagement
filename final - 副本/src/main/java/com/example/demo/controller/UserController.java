package com.example.demo.controller;

import com.example.demo.api.ResponseUser;
import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
//import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    //controller中包含从前端接收函数同时从后端给出的操作
    @Autowired
    private UserService userService;
//根据id删除
    @RequestMapping("/deleteUser")
    public Map deleteUser(int id) {
          Map result = new HashMap();
          if(this.userService.deleteUser(id)){
              result.put("isOk",true);
              result.put("status", "success");
          }else {
              result.put("isOk",false);
              result.put("status", "fail");
          }
          return result;
    }
//增添
    @RequestMapping("/addUser")
    public Map addUser(@RequestBody User user) {
        this.userService.register(user);
        Map result = new HashMap();
        result.put("isOk",true);
        result.put("status", "success");
        return result;
    }
//展示所有
    @RequestMapping("/findAllUsers")
    public Map findAllUsers() {
        List<User> list = this.userService.findAll();

        Map result = new HashMap();
        result.put("isOk", true);
        result.put("msg", "success");
        result.put("users", list);
        return result;
    }
//根据id查找
    @RequestMapping("/findUserById")
    public Map findUserById(@RequestParam int id) {
        Map result = new HashMap();
        try {
            User user = this.userService.findById(id);
            result.put("isOk", true);
            result.put("msg", "success");
            result.put("user", user);
        }catch (RuntimeException e) {
            result.put("isOk", false);
            result.put("msg", e.getMessage());
        }

        return result;
    }
//修改信息
    @RequestMapping("/updateUser")
    public Map updateUser(@RequestBody User user) {
        Map result = new HashMap();
        try {
            userService.updateUser(user);
            result.put("isOk", true);
            result.put("msg", "修改成功");
        }catch (Exception e) {
            result.put("isOk", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }
//模糊搜索
private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @RequestMapping("/searchUsers")
    public ResponseUser searchUsers(@RequestParam String query) {
        logger.info("Searching users with query: {}", query);
        List<User> users = userService.searchUsers(query);
        if (users != null&& !users.isEmpty()) {
            logger.info("Search success: found {} users", users.size());
            return new ResponseUser(true, "搜索成功", users);
        } else {
            logger.warn("Search failed: no users found");
            return new ResponseUser(false, "没有找到相关用户",null);
        }
    }

    @RequestMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginForm) {
        String username = loginForm.get("username");
        String password = loginForm.get("password");

        User user = userService.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            Map<String, Object> response = new HashMap<>();
            response.put("isOk", true);
            response.put("user", user);
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("isOk", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

        public void setUserService(UserService userService) {
            this.userService = userService;
        }

    }
