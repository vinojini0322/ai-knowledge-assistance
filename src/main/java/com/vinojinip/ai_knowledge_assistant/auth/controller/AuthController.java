package com.vinojinip.ai_knowledge_assistant.auth.controller;

import com.vinojinip.ai_knowledge_assistant.auth.dto.RegisterRequest;
import com.vinojinip.ai_knowledge_assistant.auth.dto.UserResponse;
import com.vinojinip.ai_knowledge_assistant.auth.entity.User;
import com.vinojinip.ai_knowledge_assistant.auth.mapper.UserMapper;
import com.vinojinip.ai_knowledge_assistant.auth.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AuthController {


    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        User savedUser = userService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserMapper.toResponse(savedUser));
    }
}
