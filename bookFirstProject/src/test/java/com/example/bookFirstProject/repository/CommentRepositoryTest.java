package com.example.bookFirstProject.repository;

import com.example.bookFirstProject.entity.Article;
import com.example.bookFirstProject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        //1.입력 데이터 준비
        //2.실제 데이터
        //3.예상 데이터
        //4.비교 및 검증

        Long articleId=4L;

        List<Comment> comments=commentRepository.findByArticleId(articleId);

        Article article=new Article(4L,"당신의 인생영화는?", "댓글고");
        Comment a=new Comment(1L, "Park", "굿 윌 헌팅", article );
        Comment b=new Comment(2L, "Kim", "아이엠샘", article );
        Comment c=new Comment(3L, "Choi", "쇼 생크 탈출", article);
        List<Comment> expected= Arrays.asList(a,b,c);

        assertEquals(comments.toString(), expected.toString());
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        //1.입력 데이터 준비
        //2.실제 데이터
        //3.예상 데이터
        //4.비교 및 검증

        String nickname="Park";

        List<Comment> comments=commentRepository.findByNickname(nickname);

        Comment a=new Comment(1L, nickname, "굿 윌 헌팅", new Article(4L, "당신의 인생영화는?", "댓글고"));
        Comment b=new Comment(4L, nickname, "치킨", new Article(5L, "당신의 소울푸드는?", "댓글고고"));
        Comment c=new Comment(7L, nickname, "조깅", new Article(6L, "당신의 취미는?", "댓글고고고"));

        List<Comment> expected=Arrays.asList(a,b,c);

        assertEquals(comments.toString(), expected.toString());

    }
}