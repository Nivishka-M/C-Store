package com.cstore.controller;

import com.cstore.model.cart.Cart;
import com.cstore.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cstore/api/customers/{id}")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "carts")
    public Cart getCart(@PathVariable(name = "id", required = true) Long id) {
        return cartService.getCart(id);
    }
}
