package com.enes.fitnes_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UpdateUserDTO {

    private MultipartFile image;

    @NotBlank(message = "Email gerekli bir alandır")
    @Email(message = "Geçerli bir email adresi giriniz")
    private String email;

    @NotBlank(message = "İsim boş olamaz")
    @Length(min = 3, max = 50, message = "İsim 3 ile 50 karakter arasında olmalıdır")
    private String fullName;

    private String job;

    @Size(min = 10, max = 10, message = "Telefon numarası 10 haneli olmalıdır")
    private String phone;

    private String address;

    private String summary;
}
