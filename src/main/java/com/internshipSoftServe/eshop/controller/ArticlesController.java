package com.internshipSoftServe.eshop.controller;

import com.internshipSoftServe.eshop.model.Articles;
import com.internshipSoftServe.eshop.model.Product;
import com.internshipSoftServe.eshop.repository.ArticlesRepository;
import com.internshipSoftServe.eshop.repository.ProductRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/shop")
@Api(value = "Articles", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticlesController {

    @Autowired
    private ArticlesRepository articlesRepository;
    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/articles")
    @ApiOperation(value = "Get all articles", response = Articles.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok")})
    @ResponseBody
    public  Iterable<Articles> getAllArticles(){
        return articlesRepository.findAll();
    }

    @GetMapping("/articles/{articleId}")
    @ApiOperation(value = "Get articles by id", response = Articles.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok")})
    @ResponseBody
    public Articles getOneArticle(@PathVariable("articleId") long articleId){
        return articlesRepository.findById(articleId).orElse(null);
    }

    @GetMapping("products/{productId}/articles")
    @ApiOperation(value = "Get articles by product id", response = Articles.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok")})
    public @ResponseBody Iterable<Articles> getArticleByProduct (@ApiParam(value = "Product param id", example = "1")@PathVariable ("productId") long productId){
        Product product = productRepository.findById(productId).orElseThrow(RuntimeException::new);
        return product.getArticles();
    }

    @PostMapping("/products/{productId}/articles")
    @ApiOperation(value = "Create article to product by param: id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok")})
    public ResponseEntity<Articles> createNewArticleToProduct(@ApiParam(value = "Product param id", example = "1")@PathVariable("productId") long productId,
                                                              @ApiParam(value = "Request body Article",required = true)@Valid @RequestBody Articles article){
        Product product = productRepository.findById(productId).orElseThrow(RuntimeException::new);
        article.setProduct(product);
        return ResponseEntity.ok(articlesRepository.save(article));
    }

    @PutMapping("/articles/{articleId}")
    @ApiOperation(value = "Update article")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok")})
    public ResponseEntity<Articles> updateArticle(@ApiParam(value = "Article param id", example = "1")@PathVariable("articleId") long articleId,
                                                  @ApiParam(value = "Request body Article",required = true)@RequestBody Articles article){
        return articlesRepository.findById(articleId).
                map(articles -> {articles.setName(article.getName());
                articles.setText(article.getText());
                articles.setCreated_at(article.getCreated_at());
                articles.setProduct(article.getProduct());
                Articles updatedArticle = articlesRepository.save(articles);
                return ResponseEntity.ok().body(updatedArticle);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/articles/{articleId}")
    @ApiOperation(value = "Delete Article by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok")})
    public void deleteArticle(@ApiParam(value = "Article param id", example = "1")@PathVariable Long articleId){
         articlesRepository.deleteById(articleId);
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
