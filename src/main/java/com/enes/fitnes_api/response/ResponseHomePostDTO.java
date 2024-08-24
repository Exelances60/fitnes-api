package com.enes.fitnes_api.response;

import com.enes.fitnes_api.dto.PostDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResponseHomePostDTO {
    List<PostDTO> postProgram;
    List<PostDTO> postDiets;
    List<PostDTO> postFood;
    List<PostDTO> postScience;
    List<PostDTO> postSuggestions;
}
