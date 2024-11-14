package com.example.demo.mapper;

import com.example.demo.entity.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper {
    // 根据书名模糊搜索
    @Select("SELECT * FROM books WHERE title LIKE CONCAT('%', #{query}, '%')")
    List<Book> searchBooks(@Param("query") String query);

    // 插入图书
    @Insert("INSERT INTO books(title, author, publisher, type, status) VALUES(#{title}, #{author}, #{publisher}, #{type}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertBook(Book book);

    // 根据ID查找
    @Select("SELECT * FROM books WHERE id = #{id}")
    Book findById(int id);

    // 查找所有图书
    @Select("SELECT * FROM books")
    List<Book> findAll();

    // 更新图书信息
    @Update("UPDATE books SET title = #{title}, author = #{author}, publisher = #{publisher}, type = #{type}, status = #{status} WHERE id = #{id}")
    void updateBook(Book book);

    // 根据ID删除图书
    @Delete("DELETE FROM books WHERE id = #{id}")
    boolean deleteBook(int id);

    //根据type返回数量
    @Select("SELECT type, COUNT(*) as count FROM books GROUP BY type")
    List<Map<String, Object>> getTypeStatistics();

    //找到可以借阅的图书
    @Select("SELECT * FROM books WHERE status = '可借阅'")
    List<Book> findAvailableBooks();

    @Select("SELECT * FROM books WHERE title = #{title}")
    Book findByTitle(String title);

}
