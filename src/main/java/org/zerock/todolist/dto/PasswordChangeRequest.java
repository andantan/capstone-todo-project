package org.zerock.todolist.dto;

import jakarta.validation.constraints.NotBlank; 
import jakarta.validation.constraints.Size; 


import lombok.Getter; 
import lombok.Setter; 

@Getter 
@Setter 
public class PasswordChangeRequest {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String username;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    
    private String phoneNumber;

    @NotBlank(message = "새 비밀번호는 필수 입력 값입니다.")
    private String newPassword;
}
    