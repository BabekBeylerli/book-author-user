package com.example.ingress_task.controller;

import com.example.ingress_task.model.UserDto;
import com.example.ingress_task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/{bookId}")
    public List<UserDto> findStudentsByBookId(@PathVariable Integer bookId) {
        return userService.findStudentsByBookId(bookId);
    }
}
