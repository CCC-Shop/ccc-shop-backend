package com.project.ccc_shop.order.usecase.create;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.Map;

import static java.sql.Types.NULL;

@Service
public class CreateOrderUseCase implements UseCase<CreateOrderInput, CreateOrderOutput> {

    private MySQLDriver mySQLDriver;

    public CreateOrderUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(CreateOrderInput input, CreateOrderOutput output) {
        try (Connection connection = this.mySQLDriver.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT `order` (`customer_id`, " +
                            "`shipping_fee`, " +
                            "`recipient_name`, " +
                            "`shipping_address`, " +
                            "`status`, " +
                            "`payment_method`, " +
                            "`credit_card_id`, " +
                            "`order_time`, " +
                            "`shipping_time`, " +
                            "`delivery_time`, " +
                            "`seasoning_discount_code`, " +
                            "`shipping_discount_code`)" +
                            " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

            stmt.setInt(1, input.getCustomerId());
            stmt.setInt(2, input.getShippingFee());
            stmt.setString(3, input.getRecipientName());
            stmt.setString(4, input.getShippingAddress());
            stmt.setString(5, input.getStatus().toString());
            stmt.setString(6, input.getPaymentMethod().toString());
            stmt.setString(7, input.getCreditCardId());
            stmt.setTimestamp(8, input.getOrderTime());
            stmt.setNull(9, NULL);
            stmt.setNull(10, NULL);
            if (input.getSeasoningDiscountCode() == 0) {
                stmt.setNull(11, NULL);
            } else {
                stmt.setInt(11, input.getSeasoningDiscountCode());
            }
            if (input.getShippingDiscountCode() == 0) {
                stmt.setNull(12, NULL);
            } else {
                stmt.setInt(12, input.getShippingDiscountCode());
            }

            stmt.executeUpdate();
            int orderId = getOrderId(connection, input.getCustomerId(), input.getOrderTime());

            createOrderItems(connection, orderId, input.getOrderItems());

            output.setId(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getOrderId(Connection connection, int customerId, Timestamp orderTime) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT `id` FROM `order` WHERE `customer_id`= ? && `order_time` = ?")) {
            stmt.setString(1, Integer.toString(customerId));
            stmt.setTimestamp(2, orderTime);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    return Integer.parseInt(rs.getString("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Order not found, where customer_id=" + customerId + ", order_time=" + orderTime + ".");
    }

    private void createOrderItems(Connection connection, int orderId, Map<Integer, Integer> orderItems) {

        orderItems.keySet().forEach(productId -> {
            try (PreparedStatement stmt = connection.prepareStatement(
                    "INSERT `order_items` (`order_id`, " +
                            "`product_id`, " +
                            "`quantity`)" +
                            " VALUES (?,?,?)")) {
                stmt.setInt(1, orderId);
                stmt.setInt(2, productId);
                stmt.setInt(3, orderItems.get(productId));

                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
