package com.cstore.dao.cart;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartDaoImpl implements CartDao {
    private final JdbcTemplate jdbcTemplate;

    public CartDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addToCart(Long userId, Long variantId, Integer quantity) {
        String sql = "INSERT INTO `cart_item` VALUES (?, ?, ?);";

        return jdbcTemplate.update( sql, userId, variantId, quantity);
    }

    @Override
    public int removeFromCart(Long userId, Long variantId) {
        String sql = "DELETE " +
                     "FROM cart_item " +
                     "WHERE (user_id, variant_id) = (?, ?);";

        return jdbcTemplate.update(sql, userId, variantId);
    }
}
