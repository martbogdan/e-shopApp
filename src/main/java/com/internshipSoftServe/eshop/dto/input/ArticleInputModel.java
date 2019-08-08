package com.internshipSoftServe.eshop.dto.input;

import com.internshipSoftServe.eshop.model.Article;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ArticleInputModel {
    private String name;
    private String text;
    private Date createdAt;
    private long productId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public static Article of(ArticleInputModel articleInputModel){
        return new Article(       articleInputModel.getName(),
                articleInputModel.getText(),
                articleInputModel.getCreatedAt()
        );
    }


}
