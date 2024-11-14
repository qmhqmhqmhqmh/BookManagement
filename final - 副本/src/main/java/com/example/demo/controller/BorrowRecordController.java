package com.example.demo.controller;

import com.example.demo.entity.BorrowRecord;
import com.example.demo.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/borrowRecord")
public class BorrowRecordController {

    @Autowired
    private BorrowRecordService borrowRecordService;

    @GetMapping("/findAll")
    public List<BorrowRecord> findAll() {
        return borrowRecordService.findAll();
    }


    @GetMapping("/findByUserName")
    public List<BorrowRecord> findByUserName(@RequestParam String userName) {
        return borrowRecordService.findByUserName(userName);
    }

    @PostMapping("/borrowBook")
    public ResponseEntity<?> borrowBook(@RequestBody BorrowRequest borrowRequest) {
        try {
            borrowRecordService.borrowBook(borrowRequest.getUserName(), borrowRequest.getBookName(), borrowRequest.getBorrowDate(), borrowRequest.getReturnDate());
            return ResponseEntity.ok(new Response(true, "借阅成功"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(false, "借阅失败: " + e.getMessage()));
        }
    }

    @PostMapping("/returnBook")
    public ResponseEntity<?> returnBook(@RequestBody ReturnRequest returnRequest) {
        try {
            borrowRecordService.returnBook(returnRequest.getBookName());
            return ResponseEntity.ok(new Response(true, "归还成功"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(false, "归还失败: " + e.getMessage()));
        }
    }

    static class BorrowRequest {
        private String userName;
        private String bookName;
        private LocalDateTime borrowDate;
        private LocalDateTime returnDate;

        // Getters and Setters
        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public LocalDateTime getBorrowDate() {
            return borrowDate;
        }

        public void setBorrowDate(LocalDateTime borrowDate) {
            this.borrowDate = borrowDate;
        }

        public LocalDateTime getReturnDate() {
            return returnDate;
        }

        public void setReturnDate(LocalDateTime returnDate) {
            this.returnDate = returnDate;
        }
    }

    static class ReturnRequest {
        private String bookName;

        // Getters and Setters
        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }
    }

    static class Response {
        private boolean isOk;
        private String message;

        public Response(boolean isOk, String message) {
            this.isOk = isOk;
            this.message = message;
        }

        // Getters and Setters
        public boolean isOk() {
            return isOk;
        }

        public void setOk(boolean ok) {
            isOk = ok;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
