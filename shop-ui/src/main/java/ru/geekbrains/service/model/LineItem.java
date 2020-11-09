package ru.geekbrains.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.geekbrains.controller.repr.ProductRepr;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class LineItem implements Serializable {

    private Long productId;

    private ProductRepr productRepr;

    private Integer qty;

    private String color;

    private String material;

    public LineItem(ProductRepr productRepr, String color, String material) {
        this.productId = productRepr.getId();
        this.productRepr = productRepr;
        this.color = color;
        this.material = material;
    }

    public LineItem() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductRepr getProductRepr() {
        return productRepr;
    }

    public void setProductRepr(ProductRepr productRepr) {
        this.productRepr = productRepr;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @JsonIgnore
    public BigDecimal getTotal() {
        return productRepr.getPrice().multiply(new BigDecimal(qty));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return productId.equals(lineItem.productId) &&
                Objects.equals(emptyToNull(color), emptyToNull(lineItem.color)) &&
                Objects.equals(emptyToNull(material), emptyToNull(lineItem.material));
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, color, material);
    }

    private static String emptyToNull(String val) {
        return val == null ? "" : val;
    }
}
