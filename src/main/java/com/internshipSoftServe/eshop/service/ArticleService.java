package com.internshipSoftServe.eshop.service;


import com.internshipSoftServe.eshop.dto.input.ArticleInputModel;
import com.internshipSoftServe.eshop.dto.output.ArticleOutputModel;
import com.internshipSoftServe.eshop.exeptions.NotFoundExeptions;
import com.internshipSoftServe.eshop.model.Articles;
import com.internshipSoftServe.eshop.model.Product;
import com.internshipSoftServe.eshop.repository.ArticlesRepository;
import com.internshipSoftServe.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticlesRepository articlesRepository;

    @Autowired
    private ProductRepository productRepository;

    public ArticleOutputModel update (long articleId, Articles article){
        Articles articleDB = articlesRepository.findById(articleId)
                .orElseThrow(NotFoundExeptions::new);
        articleDB.setText(article.getText());
        articleDB.setName(article.getName());
        articlesRepository.save(articleDB);
        return ArticleOutputModel.of(articleDB);
    }

    public ArticleOutputModel saveArticle (ArticleInputModel articleInputModel){
        Product product = productRepository.findById(articleInputModel.getProductId())
                .orElseThrow(NotFoundExeptions::new);
        Articles article = ArticleInputModel.of(articleInputModel);
        article.setProduct(product);
        articlesRepository.save(article);
        return ArticleOutputModel.of(article);
    }

    public List<ArticleOutputModel>findAllByProductId(Long id){
       List<Articles> articles = articlesRepository.findAll()
               .stream().filter(articles1 -> articles1.getProduct().equals(id))
               .collect(Collectors.toList());
        List<ArticleOutputModel> articleOutputModels = new ArrayList<>();
        articles.forEach(articles1 -> articleOutputModels.add(ArticleOutputModel.of(articles1)));
        return articleOutputModels;
    }

    public ArticleOutputModel findArticleById(Long articleId){
        ArticleOutputModel article = ArticleOutputModel.of(articlesRepository.findById(articleId)
                .orElseThrow(NotFoundExeptions::new));
        return article;
    }

}
