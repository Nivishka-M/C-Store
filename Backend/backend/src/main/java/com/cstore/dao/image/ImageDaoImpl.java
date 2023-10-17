package com.cstore.dao.image;

import com.cstore.model.product.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository

@RequiredArgsConstructor
public class ImageDaoImpl implements ImageDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Image> findByProductId(Long productId) {
        String sql = "SELECT * " +
                     "FROM \"images_from_product\"(?);";

        return jdbcTemplate.query(
            sql,
            preparedStatement -> preparedStatement.setLong(1, productId),
            new BeanPropertyRowMapper<>(Image.class)
        );
    }

    @Override
    public Image save(Image image) {
        String sql = "INSERT INTO \"image\"(\"url\") " +
                     "VALUES(?) " +
                     "RETURNING \"image_id\";";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
            connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, image.getUrl());

                return ps;
            },
            keyHolder
        );

        Number generatedUserId = keyHolder.getKey();

        if (generatedUserId != null) {
            image.setImageId(generatedUserId.longValue());
        }

        return image;
    }
}
