package com.example.ingress_task.service;

import com.example.ingress_task.dao.entity.BookEntity;
import com.example.ingress_task.dao.entity.NotificationEntity;
import com.example.ingress_task.dao.entity.UserEntity;
import com.example.ingress_task.dao.entity.enums.ERole;
import com.example.ingress_task.dao.repository.BookRepository;
import com.example.ingress_task.dao.repository.UserRepository;
import com.example.ingress_task.mapper.BookMapper;
import com.example.ingress_task.model.BookDto;
import com.example.ingress_task.model.BookGetDto;
import com.example.ingress_task.model.BookLightDto;
import com.example.ingress_task.model.BookLightSaveDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookMapper bookMapper;
    private final NotificationService notificationService;
    private final EmailService emailService;

    public List<BookLightDto> findBooksByUserId(Integer userId) {
        List<BookEntity> books = bookRepository.findBooksByUserId(userId);
        return bookMapper.mapEntityToLightDtos(books);
    }

    public List<BookGetDto> getAllBooks() {
        log.info("ActionLog.getAllBooks.start");
        List<BookGetDto> bookGetDtos = BookMapper.mapper.mapEntityToGetDtos2(bookRepository.findAll());
        log.info("ActionLog.getAllBooks.end");
        return bookGetDtos;
    }

    public BookDto getBook(Integer bookId) {
        log.info("ActionLog.getBook.start");
        BookEntity bookEntity =
                bookRepository.findById(bookId).orElseThrow(() ->
                        new RuntimeException("Not Found!")
                );
        log.info("ActionLog.getBook.end");
        return BookMapper.mapper.mapEntityToDto(bookEntity);
    }

    public void addBook(UserEntity author, String title) {
        try {
            BookEntity newBook = new BookEntity();
            newBook.setName(title);
            newBook.setAuthor(author);
            author.getBooks().add(newBook);
            bookRepository.save(newBook);
            notificationService.notifySubscribersAboutNewBook(author, title);
            List<UserEntity> studentSubscribers = author.getSubscribers().stream()
                    .filter(user -> user.getRoles().stream().anyMatch(role -> role.getName() == ERole.STUDENT))
                    .collect(Collectors.toList());

            for (UserEntity subscriber : studentSubscribers) {
                String subject = "New Book";
                String message = "Hello " + subscriber.getUsername() + ",\n\n add new book: " + title;
                emailService.sendEmail(subscriber.getEmail(), subject, message);
            }
        } catch (Exception e) {
            log.info("Error : " + e.getMessage());

        }

    }


    public void deleteBook(Integer bookId) {
        log.info("ActionLog.deleteBook.start");
        bookRepository.deleteById(bookId);
        log.info("ActionLog.deleteBook.end");
    }

}


