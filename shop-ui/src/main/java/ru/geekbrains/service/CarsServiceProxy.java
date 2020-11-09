package ru.geekbrains.service;

import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.service.model.LineItem;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

public class CarsServiceProxy implements CartService {

    HttpSession httpSession;

    @Override
    public void addProductQty(ProductRepr productRepr, String color, String material, int qty) {
        CartService cartService = (CartService) httpSession.getAttribute("cartService");
        cartService.addProductQty(productRepr, color, material, qty);
    }

    @Override
    public void removeProductQty(ProductRepr productRepr, String color, String material, int qty) {

    }

    @Override
    public void removeProduct(LineItem lineItem) {

    }

    @Override
    public List<LineItem> getLineItems() {
        return null;
    }

    @Override
    public BigDecimal getSubTotal() {
        return null;
    }

    @Override
    public void updateCart(LineItem lineItem) {

    }
}
