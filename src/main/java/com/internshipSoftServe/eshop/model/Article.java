package com.internshipSoftServe.eshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@Getter
@Setter
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    @Size(max = 45)
    private String name;

    @Size(max = 2550)
    private String text;

    @Column(name = "created_at")
    private Date createdAt;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private Product product;

    public Article() {
    }

    public Article(@Size(max = 45) String name, String text, Date createdAt) {
        this.name = name;
        this.text = text;
        this.createdAt = createdAt;
    }

    public Article(@Size(max = 45) String name, String text, Date createdAt, Product product) {
        this.name = name;
        this.text = text;
        this.createdAt = createdAt;
        this.product = product;
    }
    public Article(Long id, @Size(max = 45) String name, String text, Date createdAt, Product product) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.createdAt = createdAt;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) &&
                Objects.equals(name, article.name) &&
                Objects.equals(text, article.text) &&
                Objects.equals(createdAt, article.createdAt) &&
                Objects.equals(product, article.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, text, createdAt, product);
    }
}
