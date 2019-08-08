package com.internshipSoftServe.eshop.controller;

import com.internshipSoftServe.eshop.dto.input.ArticleInputModel;
import com.internshipSoftServe.eshop.dto.output.ArticleOutputModel;
import com.internshipSoftServe.eshop.model.Article;
import com.internshipSoftServe.eshop.repository.ArticlesRepository;
import com.internshipSoftServe.eshop.repository.ProductRepository;
import com.internshipSoftServe.eshop.service.ArticleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Articles", produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
@RequestMapping(value = "/shop")
public class ArticlesController {

    @Autowired
    private ArticlesRepository articlesRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "Get all articles", response = Article.class)
    @GetMapping("/articles")
    public @ResponseBody
    List<ArticleOutputModel> getAllArticles(){
        return articleService.findAllArticles();
    }


    @ApiOperation(value = "Get articles by id", response = Article.class)
    @GetMapping("/articles/{articleId}")
    public ResponseEntity<ArticleOutputModel> getArticleById(@PathVariable Long articleId) throws ChangeSetPersister.NotFoundException {
        ArticleOutputModel article = articleService.findArticleById(articleId);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }


    @ApiOperation(value = "Get articles by product id", response = Article.class)
    @GetMapping("products/{productId}/articles")
    public ResponseEntity<List<ArticleOutputModel>> getArticleByProduct (@ApiParam(value = "Product param id", example = "1")@PathVariable ("productId") Long productId){
       List<ArticleOutputModel> articles = articleService.findAllByProductId(productId);
       return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @ApiOperation(value = "Create article to product by param: productId")
    @PostMapping("/articles")
    public ResponseEntity createNewArticleToProduct(@ApiParam(value = "Request body Article",required = true)@Valid @RequestBody ArticleInputModel articleInputModel) throws ChangeSetPersister.NotFoundException {
        ArticleOutputModel articleDB = articleService.saveArticle(articleInputModel);
        return new ResponseEntity(articleDB, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update article")
    @PutMapping("/articles/{articleId}")
    public ResponseEntity<ArticleOutputModel> updateArticle(@ApiParam(value = "Article param id", example = "1")@PathVariable("articleId") long articleId,
                                                  @ApiParam(value = "Request body Article",required = true)@RequestBody Article article) throws ChangeSetPersister.NotFoundException {
        ArticleOutputModel articleDB = articleService.update(articleId, article);
        return new ResponseEntity<>(articleDB, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete Article by id")
    @DeleteMapping("/articles/{articleId}")
    public void deleteArticle(@ApiParam(value = "Article param id", example = "1")@PathVariable long articleId){
         articlesRepository.deleteById(articleId);
    }

}
