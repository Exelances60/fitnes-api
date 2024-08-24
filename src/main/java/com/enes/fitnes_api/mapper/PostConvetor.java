package com.enes.fitnes_api.mapper;

import com.enes.fitnes_api.dto.PostDTO;
import com.enes.fitnes_api.model.Post;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostConvetor {

    List<PostDTO> convertToPostDTOList(List<Post> posts);

    PostDTO convertToPostDTO(Post post);
}
