package ru.geekbrains.controller.repr;

import ru.geekbrains.persist.model.Picture;
import ru.geekbrains.persist.model.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepr implements Serializable {

    private Long id;

    private String name;

    private BigDecimal price;

    private String category;

    private String brand;

    private List<Long> pictureIds;

    public ProductRepr() {
    }

    public ProductRepr(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.category = product.getCategory().getName();
        this.brand = product.getBrand().getName();
        this.pictureIds = product.getPictures().stream()
                .map(Picture::getId).collect(Collectors.toList());
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Long> getPictureIds() {
        return pictureIds;
    }

    public void setPictureIds(List<Long> pictureIds) {
        this.pictureIds = pictureIds;
    }
}
