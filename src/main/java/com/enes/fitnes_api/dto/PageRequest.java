package com.enes.fitnes_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequest {
    @NotNull
    private Integer page;

    @NotNull
    private Integer size;
}

