package com.example.ingress_task.controller;

import com.example.ingress_task.dao.entity.NotificationEntity;
import com.example.ingress_task.dao.entity.UserEntity;
import com.example.ingress_task.service.NotificationService;
import com.example.ingress_task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final UserService userService;

    @GetMapping("/user/{userId}/notifications")
    public List<NotificationEntity> getUserNotifications(@PathVariable Integer userId) {
            UserEntity user = userService.findById(userId);
        return notificationService.getUserNotifications(user);
    }
}

