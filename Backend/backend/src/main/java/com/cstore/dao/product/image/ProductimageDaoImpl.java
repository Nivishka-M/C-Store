package com.cstore.dao.product.image;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
@RequiredArgsConstructor
public class ProductimageDaoImpl implements ProductImageDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Long imageId, Long productId) {
        String sql = "INSERT INTO \"product_image\"(\"image_id\", \"product_id\") " +
                     "VALUES(?, ?);";

        jdbcTemplate.update(
            connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                ps.setLong(1, imageId);
                ps.setLong(2, productId);

                return ps;
            }
        );
    }
}
