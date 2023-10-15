package com.cstore.dao.product.image;

import com.cstore.model.product.Image;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

@RequiredArgsConstructor
public class ImageDaoImpl implements ImageDao {
    private final JdbcTemplate jdbcTemplate;

    @Override

    @Comment("This method is perfect.")
    public List<Image> findByProductId(Long productId) {
        String sql = "SELECT * " +
                     "FROM \"images_from_product\"(?);";

        return jdbcTemplate.query(
                sql,
                preparedStatement -> preparedStatement.setLong(1, productId),
                new BeanPropertyRowMapper<>(Image.class)
        );
    }
}
