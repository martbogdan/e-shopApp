package com.internshipSoftServe.eshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
@Table(name = "products")

@Data
public class Product {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    @Size(max = 45)
    private String name;

    private int price;

    private int quantity;

    @Column(length = 255)
    @Size(max = 255)
    private String description;
    
    @Column(length = 255)
    @Size(max = 255)
    private String photo;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    private Category category;
    
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;
    
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Article> articles;
    
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Comment> comments;

    public Product(String name, double price, int quantity, String description, String photo) {
    }

	public Product() {
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public long getCategoryId() {
		return category.getId();
	}
	
	public void setCategoryId(long categoryId) {
		this.category = new Category();
		this.category.setId(categoryId);
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}


}

