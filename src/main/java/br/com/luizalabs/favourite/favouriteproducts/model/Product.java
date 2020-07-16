package br.com.luizalabs.favourite.favouriteproducts.model;

import java.io.Serializable;

public class Product implements Serializable {
    private float price;
    private String image;
    private String brand;
    private String id;
    private String title;
    private float reviewScore;

    public Product(float price, String image, String brand, String id, String title, float reviewScore) {
        this.price = price;
        this.image = image;
        this.brand = brand;
        this.id = id;
        this.title = title;
        this.reviewScore = reviewScore;
    }

    public Product() {
    }

    public Product(float price, String image, String brand, String id, String title) {
        this.price = price;
        this.image = image;
        this.brand = brand;
        this.id = id;
        this.title = title;
        this.reviewScore = reviewScore;


    }

    public float getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getBrand() {
        return brand;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public float getReviewScore() {
        return reviewScore;
    }
}
