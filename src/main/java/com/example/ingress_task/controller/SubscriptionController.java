package com.example.ingress_task.controller;

import com.example.ingress_task.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/subscription")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/subscribeToAuthor")
    @PreAuthorize("hasRole('STUDENT')")
    public void subscribeToAuthor(@RequestParam Integer authorId) {
        subscriptionService.subscribeToAuthor(authorId);
    }
}
