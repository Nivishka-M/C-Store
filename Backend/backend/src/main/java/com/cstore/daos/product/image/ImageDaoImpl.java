package com.cstore.daos.product.image;

import com.cstore.models.Category;
import com.cstore.models.Image;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageDaoImpl implements ImageDao {
    private final JdbcTemplate jdbcTemplate;

    public ImageDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Image> findByProductId(Long productId) {
        String sql = "CALL images_from_product(?);";

        return jdbcTemplate.query(
                sql,
                preparedStatement -> preparedStatement.setLong(1, productId),
                new BeanPropertyRowMapper<>(Image.class)
        );
    }
}
