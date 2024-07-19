package com.example.bookFirstProject.repository;

import com.example.bookFirstProject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll(); //findAll()의 반환값이 iterable인데 ArrayList를 반환하도록 수정해주기
}
