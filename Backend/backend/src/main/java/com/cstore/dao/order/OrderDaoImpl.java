package com.cstore.dao.order;

import com.cstore.model.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDaoImpl implements OrderDao {
    String url = "jdbc:mysql://localhost:3306/cstore";
    String username = "cadmin";
    String password = "cstore_GRP28_CSE21";

    Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<Order>();
        String sql = "SELECT * " +
                     "FROM `order`;";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);) {

            while (resultSet.next()) {
                Order order = new Order();

                order.setOrderId(resultSet.getLong("order_id"));

                Timestamp timestamp = resultSet.getTimestamp("date");
                if (timestamp != null) {
                    order.setDate(timestamp.toInstant());
                } else {
                    order.setDate(null);
                }

                order.setTotalPayment(resultSet.getBigDecimal("total_payment"));
                order.setPaymentMethod(resultSet.getString("payment_method"));
                order.setDeliveryMethod(resultSet.getString("delivery_method"));
                order.setEmail(resultSet.getString("email"));
                order.setStreetNumber(resultSet.getString("street_number"));
                order.setStreetName(resultSet.getString("street_name"));
                order.setCity(resultSet.getString("city"));
                order.setZipcode(resultSet.getInt("zipcode"));

                orders.add(order);
            }
            return orders;
        } catch (SQLException sqe) {
            logger.error("Error while fetching orders from the database.", sqe);
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        Order order = new Order();
        String sql = "SELECT * " +
                     "FROM `order` " +
                     "WHERE `order_id` = ?;";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, orderId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    order.setOrderId(resultSet.getLong("order_id"));

                    Timestamp timestamp = resultSet.getTimestamp("date");
                    if (timestamp != null) {
                        order.setDate(timestamp.toInstant());
                    } else {
                        order.setDate(null);
                    }

                    order.setTotalPayment(resultSet.getBigDecimal("total_payment"));
                    order.setPaymentMethod(resultSet.getString("payment_method"));
                    order.setDeliveryMethod(resultSet.getString("delivery_method"));
                    order.setEmail(resultSet.getString("email"));
                    order.setStreetNumber(resultSet.getString("street_number"));
                    order.setStreetName(resultSet.getString("street_name"));
                    order.setCity(resultSet.getString("city"));
                    order.setZipcode(resultSet.getInt("zipcode"));

                    return Optional.of(order);
                } else {
                    return Optional.empty();
                }
            }

        } catch (SQLException sqe) {
            logger.error("Error while fetching orders from the database.", sqe);
            return Optional.empty();
        }
    }

    @Override
    public void deleteAll() throws SQLException {
        String sql = "DELETE FROM `order`;";

        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    @Override
    public void deleteByID(Long orderId) throws SQLException {
        String sql = "DELETE FROM `order`" +
                     "WHERE `order_id` = ?";

        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, orderId);
        preparedStatement.executeUpdate();
    }
}
