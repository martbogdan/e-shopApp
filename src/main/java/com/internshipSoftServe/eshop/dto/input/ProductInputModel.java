package com.internshipSoftServe.eshop.dto.input;

import com.internshipSoftServe.eshop.model.Product;
import lombok.Getter;
import lombok.Setter;


@Setter @Getter
public class ProductInputModel {
    private String name;
    private double price;
    private int quantity;
    private String description;
    private String photo;
    private long categoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public static Product of (ProductInputModel model){
        return new Product(
                model.getName(),
                model.getPrice(),
                model.getQuantity(),
                model.getDescription(),
                model.getPhoto()
        );
    }
}
