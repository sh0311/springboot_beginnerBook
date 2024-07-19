package com.example.bookFirstProject.service;

import com.example.bookFirstProject.repository.ArticleRepository;
import com.example.bookFirstProject.repository.CommentRepository;
import com.example.bookFirstProject.dto.CommentDto;
import com.example.bookFirstProject.entity.Article;
import com.example.bookFirstProject.entity.Comment;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(long articleId){
        /*
        //1.댓글조회
        List<Comment> comments=commentRepository.findByArticleId(articleId);
        //2.엔티티->Dto 변환
        List<CommentDto> dtos=new ArrayList<>();
        for(int i=0;i<comments.size();i++){
            CommentDto dto=CommentDto.createCommentDto(comments.get(i));
            dtos.add(dto);
        }
        //3.결과 반환
        return dtos;
        */
         return commentRepository.findByArticleId(articleId)
                 .stream()
                 .map(comment->CommentDto.createCommentDto(comment))
                 .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto){
        // 1. 게시글 조회 및 예외 발생
        Article article=articleRepository.findById(articleId).orElseThrow(()->new IllegalArgumentException("댓글 생성 실패!"+"대상 게시글이 없습니다"));
        // 2. 댓글 엔티티 생성
        Comment comment=Comment.createComment(article,dto);
        // 3. 댓글 엔티티를 db에 저장
        Comment created=commentRepository.save(comment);
        // 4. Dto로 변환해 반환
        return CommentDto.createCommentDto(created);
    }
}
