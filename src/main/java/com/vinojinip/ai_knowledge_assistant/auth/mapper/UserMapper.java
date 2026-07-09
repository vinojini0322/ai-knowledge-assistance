package com.vinojinip.ai_knowledge_assistant.auth.mapper;

import com.vinojinip.ai_knowledge_assistant.auth.dto.RegisterRequest;
import com.vinojinip.ai_knowledge_assistant.auth.dto.UserResponse;
import com.vinojinip.ai_knowledge_assistant.auth.entity.User;

public class UserMapper {
    private UserMapper() {
    }

    public static User toEntity(RegisterRequest request, String encodedPassword) {
        return User.builder()
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .password(encodedPassword)
                .build();
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName());
    }
}
