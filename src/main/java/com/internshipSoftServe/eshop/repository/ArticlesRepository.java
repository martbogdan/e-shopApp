package com.internshipSoftServe.eshop.repository;

import com.internshipSoftServe.eshop.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticlesRepository extends JpaRepository<Article, Long> {
    Optional<Article> findById(Long id);
    void deleteById (long articleId);
}
