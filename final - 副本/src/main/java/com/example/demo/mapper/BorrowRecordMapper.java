package com.example.demo.mapper;

import com.example.demo.entity.BorrowRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BorrowRecordMapper {
    //根据user_name找到记录
    @Select("SELECT * FROM borrow_records WHERE user_name = #{user_name}")
    List<BorrowRecord> findByUserName(String user_name);

    //展示所有记录
    @Select("SELECT * FROM borrow_records")
    List<BorrowRecord> findAll();

    //新添记录
    @Insert("INSERT INTO borrow_records(user_name, book_name, borrow_date, return_date, status) VALUES(#{user_name}, #{book_name}, #{borrow_date}, #{return_date}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addBorrowRecord(BorrowRecord borrowRecord);

    //删除记录
    @Delete("DELETE FROM borrow_records WHERE book_name = #{book_name}")
    void deleteByBookName(String book_name);
}
