package com.enes.fitnes_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criterion {
    @NotBlank
    private String filterKey;

    @NotBlank
    private String operation;

    @NotBlank
    private String value;
}
