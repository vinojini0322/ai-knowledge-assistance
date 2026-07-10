package com.vinojinip.ai_knowledge_assistant.auth.controller;

import com.vinojinip.ai_knowledge_assistant.auth.dto.ProfileResponse;
import com.vinojinip.ai_knowledge_assistant.auth.entity.User;
import com.vinojinip.ai_knowledge_assistant.auth.mapper.UserMapper;
import com.vinojinip.ai_knowledge_assistant.chat.service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final CurrentUserService currentUserService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<ProfileResponse> getProfile() {
        User user = currentUserService.getCurrentUser();

        return ResponseEntity.ok(
                userMapper.toProfileResponse(user)
        );
    }
}