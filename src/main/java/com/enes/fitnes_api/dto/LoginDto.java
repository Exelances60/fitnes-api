package com.enes.fitnes_api.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank(message = "Email Boş Bırakılamaz")
    @Email(message = "Email Geçerli Bir Email Adresi Olmalıdır")
    private String email;

    @NotBlank(message = "Şifre Boş Bırakılamaz")
    @Length(min = 6, message = "Şifre En Az 6 Karakter Olmalıdır")
    private String password;
}
