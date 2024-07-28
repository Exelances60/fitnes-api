package com.enes.fitnes_api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDTO {
    private String email;
    private String fullName;
    private String token;

    public ResponseUserDTO(String email, String fullName) {
        this.email = email;
        this.fullName = fullName;
    }
}
