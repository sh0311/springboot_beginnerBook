package com.example.bookFirstProject.entity;

import com.example.bookFirstProject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickname;

    @Column
    private String body;

    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;

    public static Comment createComment(Article article, CommentDto dto){
        if(dto.getId()!=null){
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야합니다");
        }
        if(dto.getArticleId()!=article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다");
        return new Comment(null, dto.getNickname(), dto.getBody(), article);  //Comment 엔티티의 필드들을 담아야 함
    }

}


