package com.example.bookFirstProject.dto;

import com.example.bookFirstProject.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long id;
    private String nickname;
    private String body;
    @JsonProperty("article_id")
    private Long ArticleId;

    public static CommentDto createCommentDto(Comment comment){
        return new CommentDto(
                comment.getId(),
                comment.getNickname(),
                comment.getBody(),
                comment.getArticle().getId()
        );

    }
}
