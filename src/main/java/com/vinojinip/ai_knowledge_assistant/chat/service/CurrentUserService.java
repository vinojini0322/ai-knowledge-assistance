package com.vinojinip.ai_knowledge_assistant.chat.service;

import com.vinojinip.ai_knowledge_assistant.auth.entity.User;
import com.vinojinip.ai_knowledge_assistant.auth.service.UserService;
import com.vinojinip.ai_knowledge_assistant.security.modal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserService {

    private final UserService userService;

        public User getCurrentUser() {
            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null
                    || !authentication.isAuthenticated()
                    || !(authentication.getPrincipal()
                    instanceof CustomUserDetails customUserDetails)) {

                throw new IllegalStateException("Authenticated user not found");
            }

            return customUserDetails.getUser();
        }

}
