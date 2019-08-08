package com.internshipSoftServe.eshop.dto.output;

import com.internshipSoftServe.eshop.model.Article;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ArticleOutputModel {
    private final long id;
    private final String name;
    private final String text;
    private final Date createdAt;
    private final long productId;

    public ArticleOutputModel(long id, String name, String text, Date createdAt, long productId) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.createdAt = createdAt;
        this.productId = productId;
    }

    public static ArticleOutputModel of (Article article){
        return new ArticleOutputModel(
          article.getId(),
          article.getName(),
          article.getText(),
          article.getCreatedAt(),
          article.getProduct().getId()
        );
    }
}
