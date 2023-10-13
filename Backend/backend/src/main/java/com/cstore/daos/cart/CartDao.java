package com.cstore.daos.cart;

public interface CartDao {
    int addToCart(Long userId, Long variantId, Integer count);

    int removeFromCart(Long userId, Long variantId);
}
