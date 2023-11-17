package com.example.ingress_task.controller;

import com.example.ingress_task.dao.entity.UserEntity;
import com.example.ingress_task.dao.repository.UserRepository;
import com.example.ingress_task.exception.NotFoundException;
import com.example.ingress_task.model.BookGetDto;
import com.example.ingress_task.model.BookLightDto;
import com.example.ingress_task.service.BookService;
import com.example.ingress_task.service.EmailService;
import com.example.ingress_task.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final UserRepository userService;


    @GetMapping("/public")
    public List<BookGetDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/author/add")
    @PreAuthorize("hasRole('AUTHOR')")
    public void addBook(@RequestParam Integer authorId, @RequestParam String title) {
        UserEntity author = userService.findById(authorId).orElse(null);
        if (author != null) {
            bookService.addBook(author, title);
        } else {
            throw new NotFoundException("Kullanıcı bulunamadı. Kimlik: " + authorId);
        }
    }



    @GetMapping("/student/{userId}")
    public List<BookLightDto> findBooksByUserId(@PathVariable Integer userId) {
        return bookService.findBooksByUserId(userId);
    }

    @DeleteMapping("/author/{bookId}")
    @PreAuthorize("hasRole('AUTHOR')")
    public void deleteBook(@PathVariable Integer bookId) {
        bookService.deleteBook(bookId);
    }
}
