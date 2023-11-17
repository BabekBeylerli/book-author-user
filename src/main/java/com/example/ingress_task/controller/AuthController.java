package com.example.ingress_task.controller;

import com.example.ingress_task.model.auth.AuthRequestDto;
import com.example.ingress_task.model.auth.AuthenticationDto;
import com.example.ingress_task.model.auth.UserRegisterRequestDto;
import com.example.ingress_task.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public void register(
            @RequestBody UserRegisterRequestDto requestDto
    ) {
        authService.register(requestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationDto> login(
            @RequestBody AuthRequestDto authRequestDto
    ) {
        return ResponseEntity.ok(authService.authenticate(authRequestDto));
    }
}