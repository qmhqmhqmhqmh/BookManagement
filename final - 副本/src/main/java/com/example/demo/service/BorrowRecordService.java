package com.example.demo.service;

import com.example.demo.entity.BorrowRecord;
import com.example.demo.entity.Book;
import com.example.demo.mapper.BorrowRecordMapper;
import com.example.demo.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BorrowRecordService {
    @Autowired
    private BorrowRecordMapper borrowRecordMapper;

    @Autowired
    private BookMapper bookMapper;

    public List<BorrowRecord> findAll() {
        return borrowRecordMapper.findAll();
    }


    public List<BorrowRecord> findByUserName(String userName) {
        return borrowRecordMapper.findByUserName(userName);
    }

    public BorrowRecord borrowBook(String userName, String bookName, LocalDateTime borrowDate, LocalDateTime returnDate) {
        Book book = bookMapper.findAll().stream()
                .filter(b -> b.getTitle().equals(bookName))
                .findFirst()
                .orElse(null);
        if (book == null || !book.getStatus().equals("可借阅")) {
            throw new RuntimeException("图书不可借阅");
        }

        book.setStatus("借阅中");
        bookMapper.updateBook(book);

        BorrowRecord record = new BorrowRecord();
        record.setuser_name(userName);
        record.setBook_name(bookName);
        record.setBorrowDate(borrowDate);
        record.setReturn_date(returnDate);
        record.setStatus("借阅中");

        borrowRecordMapper.addBorrowRecord(record);
        return record;
    }

    public void returnBook(String bookName) {
        Book book = bookMapper.findAll().stream()
                .filter(b -> b.getTitle().equals(bookName))
                .findFirst()
                .orElse(null);
        if (book == null) {
            throw new RuntimeException("未找到图书");
        }

        book.setStatus("可借阅");
        bookMapper.updateBook(book);

        borrowRecordMapper.deleteByBookName(bookName);
    }
}
