package com.internshipSoftServe.eshop.service;


import com.internshipSoftServe.eshop.repository.ArticlesRepository;
import com.internshipSoftServe.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    private ArticlesRepository articlesRepository;

    @Autowired
    private ProductRepository productRepository;



}
