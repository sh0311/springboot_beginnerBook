package com.example.bookFirstProject.service;

import com.example.bookFirstProject.dto.ArticleForm;
import com.example.bookFirstProject.entity.Article;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;

    @Test
    void index() {
        //1.예상데이터
        Article a=new Article(1L,"aa", "111");
        Article b=new Article(2L,"bb", "222");
        Article c=new Article(3L,"cc", "333");
        List<Article> expected=new ArrayList<Article>(Arrays.asList(a,b,c));

        //2.실제 데이터
        List<Article> articles=articleService.index();
        //3.비교 및 검증
        assertEquals(expected.toString(),articles.toString());
    }

    @Test
    @Transactional
    void show_성공_존재하는_id_입력(){
        //1.예상데이터
        Long id=1L;
        Article expected=new Article(id,"aa", "111");

        //2.실제 데이터
        Article article=articleService.show(id);

        //3.비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패_존재하지않는_id_입력(){
        //1.예상데이터
        Long id=-1L;
        Article expected=null;

        //2.실제 데이터
        Article article=articleService.show(id);

        //3.비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    void create_성공(){
        //1.예상데이터
        String title="dd";
        String content="4444";
        ArticleForm dto=new ArticleForm(null, title, content);
        Article expected=new Article(4L, title, content);

        //2.실제 데이터
        Article article=articleService.create(dto);

        //3.비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void create_실패(){
        //1.예상데이터
        String title="dd";
        String content="4444";
        Long id=4L;
        ArticleForm dto=new ArticleForm(id, title, content);
        Article expected= null;

        //2.실제 데이터
        Article article=articleService.create(dto);

        //3.비교 및 검증
        assertEquals(expected, article);
    }
}