package com.enes.fitnes_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CriteriaRequest extends PageRequest {
    List<Criterion> criteria;
}