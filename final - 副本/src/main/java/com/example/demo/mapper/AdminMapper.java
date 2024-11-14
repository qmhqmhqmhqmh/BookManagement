package com.example.demo.mapper;

import com.example.demo.entity.Admin;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AdminMapper {

    @Select("SELECT * FROM admins WHERE username = #{username}")
    Admin findByUsername(@Param("username") String username);

    @Insert("INSERT INTO admins (username, password, email, tel) VALUES (#{username}, #{password}, #{email}, #{tel})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertAdmin(Admin admin);
}
