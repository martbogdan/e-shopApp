package com.internshipSoftServe.eshop.service;


import com.internshipSoftServe.eshop.dto.input.ArticleInputModel;
import com.internshipSoftServe.eshop.dto.output.ArticleOutputModel;
import com.internshipSoftServe.eshop.model.Article;
import com.internshipSoftServe.eshop.model.Product;
import com.internshipSoftServe.eshop.repository.ArticlesRepository;
import com.internshipSoftServe.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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


    public ArticleOutputModel update (Long articleId, Article article) throws ChangeSetPersister.NotFoundException {
        Article articleDB = articlesRepository.findById(articleId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        articleDB.setText(article.getText());
        articleDB.setName(article.getName());
        articleDB.setCreatedAt(article.getCreatedAt());
        articlesRepository.save(articleDB);
        return ArticleOutputModel.of(articleDB);
    }

    public ArticleOutputModel saveArticle (ArticleInputModel articleInputModel) throws ChangeSetPersister.NotFoundException {
        Product product = productRepository.findById(articleInputModel.getProductId())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        Article article = ArticleInputModel.of(articleInputModel);
        article.setProduct(product);
        articlesRepository.save(article);
        return ArticleOutputModel.of(article);
    }

    public List<ArticleOutputModel> findAllByProductId (Long id){
        List<Article> articles = articlesRepository.findAll()
                .stream().filter(article -> article.getProduct().getId().equals(id))
                .collect(Collectors.toList());
        List<ArticleOutputModel> articleOutputModels = new ArrayList<>();
        articles.forEach(article -> articleOutputModels.add(ArticleOutputModel.of(article)));
        return articleOutputModels;
    }
    public ArticleOutputModel findArticleById(Long articleId) throws ChangeSetPersister.NotFoundException {
        ArticleOutputModel articleOutputModel = ArticleOutputModel.of(articlesRepository.findById(articleId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new));
        return articleOutputModel;
    }

    public List<ArticleOutputModel> findAllArticles(){
        List<Article> articles = articlesRepository.findAll()
                .stream().collect(Collectors.toList());
        List<ArticleOutputModel> articleOutputModels = new ArrayList<>();
        articles.forEach(article -> articleOutputModels.add(ArticleOutputModel.of(article)));
        return articleOutputModels;
    }



}
