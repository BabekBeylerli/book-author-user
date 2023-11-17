package com.example.ingress_task.controller;

import com.example.ingress_task.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/email")
@RequiredArgsConstructor
public class EmailController {
    private final SubscriptionService subscriptionService;

}
