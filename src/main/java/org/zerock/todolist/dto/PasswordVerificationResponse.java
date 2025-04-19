package org.zerock.todolist.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordVerificationResponse {
    private String token; // 발급된 임시 토큰

    public PasswordVerificationResponse(String token) {
        this.token = token;
    }
}