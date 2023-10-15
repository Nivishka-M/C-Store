package com.cstore.dao.variant;

import com.cstore.model.product.Variant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VariantDaoImpl implements VariantDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public VariantDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Variant> findById(Long variantId) {
        String sql = "SELECT * " +
                     "FROM `variant` " +
                     "WHERE `variant_id` = ?;";

        try {
            Variant variant = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Variant.class), variantId);

            return Optional.of(variant);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Variant> findByProperty(Long propertyId) {
        String sql = "SELECT DISTINCT `variant_id`, `weight` " +
                     "FROM `varies_on` NATURAL LEFT OUTER JOIN `variant` " +
                     "WHERE `property_id` = ?;";

        return jdbcTemplate.query(
                sql,
                preparedStatement -> preparedStatement.setLong(1, propertyId),
                new BeanPropertyRowMapper<>(Variant.class)
        );
    }

    @Override
    public Integer countStocks(Long variantId) {
        String sql = "SELECT SUM(`count`) " +
                     "FROM `inventory` " +
                     "WHERE `variant_id` = ?;";

        return jdbcTemplate.queryForObject(sql, Integer.class, variantId);
    }
}
