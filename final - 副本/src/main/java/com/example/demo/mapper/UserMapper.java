package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    //根据用户名查找
    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(@Param("username") String username);

    //插入
    @Insert("INSERT INTO users(username, password, email, tel) VALUES(#{username}, #{password}, #{email}, #{tel})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

    //根据ID查找
    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(int id);

    //查找所有
    @Select("SELECT * FROM users")
    List<User> findAll();

    //更新
    @Update("UPDATE users SET username = #{username}, password = #{password}, email = #{email}, tel = #{tel} WHERE id = #{id}")
    void updateUser(User user);

    //根据ID删除
    @Delete("DELETE FROM users WHERE id = #{id}")
    boolean deleteUser(int id);

    // 检查用户名是否存在
    @Select("SELECT COUNT(*) FROM users WHERE username = #{username}")
    boolean existsByUsername(String username);

    //模糊搜索
    @Select("SELECT * FROM users WHERE username LIKE CONCAT('%', #{query}, '%')")
    List<User> searchUsers(@Param("query") String query);
}

