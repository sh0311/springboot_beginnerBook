package com.example.bookFirstProject.service;

import com.example.bookFirstProject.repository.ArticleRepository;
import com.example.bookFirstProject.dto.ArticleForm;
import com.example.bookFirstProject.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index(){
        return articleRepository.findAll();
    }

    public Article show(Long id){
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto){
        Article article=dto.toEntity();
        if(article.getId()!=null){
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto, boolean patch){
        Article article=dto.toEntity(); //dto 객체의 toEntity() 메소드 호출하여 Article 객체 생성
        Article target=articleRepository.findById(id).orElse(null);
        if(target==null||id!=article.getId()){
            return null;
        }
        if(patch){
            target.patch(article);
            Article updated= articleRepository.save(target);
            return updated;
        }

        else{
            Article updated= articleRepository.save(article);
            return updated;
        }
    }

    public Article delete(Long id){
        Article target=articleRepository.findById(id).orElse(null);
        if(target==null){
            return null;
        }
        articleRepository.delete(target);
        return target;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos){
        List<Article> articleList=dtos.stream()
                .map(dto->dto.toEntity())
                .collect(Collectors.toList());
        articleList.stream()
                .forEach(article->articleRepository.save(article));
        articleRepository.findById(-1L)
                .orElseThrow(()->new IllegalArgumentException("결제실패"));
        return articleList;
    }




}
