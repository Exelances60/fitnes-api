package com.enes.fitnes_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostDTO {
    @Size(min = 3, max = 50, message = "Başlık 3-50 karakter arasında olmalıdır.")
    @NotBlank(message = "Başlık boş olamaz.")
    private String title;

    @NotBlank(message = "İçerik boş olamaz.")
    private String content;

    private String image;
}
