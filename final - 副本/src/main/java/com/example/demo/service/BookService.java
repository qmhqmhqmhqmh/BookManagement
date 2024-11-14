package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    public List<Book> searchBooks(String query) {
        return bookMapper.searchBooks(query);
    }

    public void addBook(Book book) {
        bookMapper.insertBook(book);
    }

    public Book findById(int id) {
        return bookMapper.findById(id);
    }

    public List<Book> findAll() {
        return bookMapper.findAll();
    }

    public void updateBook(Book book) {
        bookMapper.updateBook(book);
    }

    public boolean deleteBook(int id) {
        return bookMapper.deleteBook(id);
    }

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public List<Map<String, Object>> getTypeStatistics() {
        return bookMapper.getTypeStatistics();
    }

    public List<Book> findAvailableBooks() {
        return bookMapper.findAvailableBooks();
    }
}
