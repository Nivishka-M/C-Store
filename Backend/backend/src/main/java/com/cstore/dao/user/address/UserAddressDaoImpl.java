package com.cstore.dao.user.address;

import com.cstore.model.user.UserAddress;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserAddressDaoImpl implements UserAddressDao {
    private final JdbcTemplate jdbcTemplate;

    public UserAddressDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserAddress save(UserAddress userAddress) {
        String sql = "INSERT INTO \"user_address\" (\"user_id\", \"street_number\", \"street_name\", \"city\", \"zipcode\") VALUES(?, ?, ?, ?, ?) RETURNING \"address_id\";";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
            connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                ps.setLong(1, userAddress.getUser().getUserId());
                ps.setString(2, userAddress.getStreetNumber());
                ps.setString(3, userAddress.getStreetName());
                ps.setString(4, userAddress.getCity());
                ps.setInt(5, userAddress.getZipcode());

                return ps;
            },
            keyHolder
        );

        Number generatedAddressId = keyHolder.getKey();

        if (generatedAddressId != null) {
            userAddress.setAddressId(generatedAddressId.longValue());
        }

        return userAddress;
    }
}
