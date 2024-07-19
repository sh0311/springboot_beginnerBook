package com.example.bookFirstProject.controller;

import com.example.bookFirstProject.repository.ArticleRepository;
import com.example.bookFirstProject.dto.ArticleForm;
import com.example.bookFirstProject.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        //System.out.println(form.toString());
        log.info(form.toString());

        Article article=form.toEntity();
        //System.out.println(article.toString());
        log.info(article.toString());

        Article saved=articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());

        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id= "+id);
        Article articleEntity=articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        List<Article> articleEntityList=articleRepository.findAll();
        model.addAttribute("articleList",articleEntityList);
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Article articleEntity=articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        Article articleEntity = form.toEntity();
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if (target != null) {
            articleRepository.save(articleEntity);
        }
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        Article target=articleRepository.findById(id).orElse(null);
        if(target!=null){
            articleRepository.delete(target);
        }
        rttr.addFlashAttribute("msg", "삭제됐습니다");
        return "redirect:/articles";
    }


}
