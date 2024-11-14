package com.example.demo.controller;

import com.example.demo.api.ResponseBook;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    //模糊搜索
    @RequestMapping("/searchBooks")
    public ResponseBook searchBooks(@RequestParam String query) {
        logger.info("Searching books with query: {}", query);
        List<Book> books = bookService.searchBooks(query);
        if (books != null && !books.isEmpty()) {
            logger.info("Search success: found {} books", books.size());
            return new ResponseBook(true, "搜索成功", books);
        } else {
            logger.warn("Search failed: no books found");
            return new ResponseBook(false, "没有找到相关图书", null);
        }
    }

    //增添
    @RequestMapping("/addBook")
    public Map addBook(@RequestBody Book book) {
        bookService.addBook(book);
        Map result = new HashMap();
        result.put("isOk", true);
        result.put("status", "success");
        return result;
    }

    @RequestMapping("/findAllBooks")
    public Map findAllBooks() {
        List<Book> list = bookService.findAll();
        Map result = new HashMap();
        result.put("isOk", true);
        result.put("msg", "success");
        result.put("books", list);
        return result;
    }

    @RequestMapping("/findBookById")
    public Map findBookById(@RequestParam int id) {
        Map result = new HashMap();
        try {
            Book book = bookService.findById(id);
            result.put("isOk", true);
            result.put("msg", "success");
            result.put("book", book);
        } catch (RuntimeException e) {
            result.put("isOk", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @RequestMapping("/updateBook")
    public Map updateBook(@RequestBody Book book) {
        Map result = new HashMap();
        try {
            bookService.updateBook(book);
            result.put("isOk", true);
            result.put("msg", "修改成功");
        } catch (Exception e) {
            result.put("isOk", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @RequestMapping("/deleteBook")
    public Map deleteBook(@RequestParam int id) {
        Map result = new HashMap();
        if (bookService.deleteBook(id)) {
            result.put("isOk", true);
            result.put("status", "success");
        } else {
            result.put("isOk", false);
            result.put("status", "fail");
        }
        return result;
    }

    @RequestMapping("/availableBooks")
    public ResponseEntity<?> getAvailableBooks() {
        List<Book> availableBooks = bookService.findAvailableBooks();
        Map<String, Object> response = new HashMap<>();
        response.put("isOk", true);
        response.put("books", availableBooks);
        return ResponseEntity.ok(response);
    }

    @RequestMapping("/typeStatistics")
    public ResponseEntity<Map<String, Object>> getTypeStatistics() {
        List<Map<String, Object>> statistics = bookService.getTypeStatistics();
        Map<String, Object> response = new HashMap<>();
        response.put("isOk", true);
        response.put("statistics", statistics);
        return ResponseEntity.ok(response);
    }
}
