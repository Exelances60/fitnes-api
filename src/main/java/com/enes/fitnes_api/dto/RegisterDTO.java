package com.enes.fitnes_api.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDTO {

    @NotBlank(message = "İsim boş olamaz")
    @Length(min = 3, message = "İsim en az 3 karakter olmalıdır")
    private String fullName;

    @NotBlank(message = "Email boş olamaz")
    @Email
    private String email;

    @NotBlank(message = "Şifre boş olamaz")
    @Length(min = 6, message = "Şifre en az 6 karakter olmalıdır")
    private String password;

}
