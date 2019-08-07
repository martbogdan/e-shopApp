package com.internshipSoftServe.eshop.dto.output;

import com.internshipSoftServe.eshop.model.Articles;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ArticleOutputModel {
    private final long id;
    private final String name;
    private final String text;
    private final LocalDateTime createdAt;
    private final long productId;

    public ArticleOutputModel(long id, String name, String text, LocalDateTime createdAt, long productId) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.createdAt = createdAt;
        this.productId = productId;
    }

    @org.jetbrains.annotations.NotNull
    @org.jetbrains.annotations.Contract("_ -> new")
    public static ArticleOutputModel of (Articles article){
        return new ArticleOutputModel(
          article.getId(),
          article.getName(),
          article.getText(),
          article.getCreated_at(),
          article.getProduct().getId()
        );
    }
}
