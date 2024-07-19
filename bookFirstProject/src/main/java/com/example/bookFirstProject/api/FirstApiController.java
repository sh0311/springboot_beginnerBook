package com.example.bookFirstProject.api;

import com.example.bookFirstProject.dto.ArticleForm;
import com.example.bookFirstProject.entity.Article;
import com.example.bookFirstProject.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class FirstApiController {
    @Autowired
    private ArticleService articleService;


    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }

    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created=articleService.create(dto);
        return (created!=null)?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        return updateArticle(id, dto, false);
    }

    @PatchMapping("api/articles/{id}")
    public ResponseEntity<Article> patch(@PathVariable Long id, @RequestBody ArticleForm dto) {
        return updateArticle(id, dto, true);
    }

    private ResponseEntity<Article> updateArticle(Long id, ArticleForm dto, boolean patch){
        Article updated=articleService.update(id, dto, patch);
        return (updated!=null)?ResponseEntity.status(HttpStatus.OK).body(updated):ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted=articleService.delete(id);
        return (deleted==null)?
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
            :ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createdList=articleService.createArticles(dtos);
        return (createdList!=null)
                ?ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();


    }
}
