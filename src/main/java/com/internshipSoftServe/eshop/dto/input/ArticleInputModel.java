package com.internshipSoftServe.eshop.dto.input;

import com.internshipSoftServe.eshop.model.Articles;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleInputModel {
    private String name;
    private String text;
    private LocalDateTime createdAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static Articles of(ArticleInputModel articleInputModel){
        return new Articles(       articleInputModel.getName(),
                articleInputModel.getText(),
                articleInputModel.getCreatedAt()
        );
    }


}
