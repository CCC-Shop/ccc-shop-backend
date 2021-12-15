package com.project.ccc_shop.order.usecase.create;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.Instant;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;

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
                            "`seasoning_discount_code`, " +
                            "`shipping_discount_code`)" +
                            " VALUES (?,?,?,?,?,?,?,?,?,?)");

            stmt.setInt(1, input.getCustomerId());
            stmt.setInt(2, input.getShippingFee());
            stmt.setString(3, input.getRecipientName());
            stmt.setString(4, input.getShippingAddress());
            stmt.setString(5, input.getStatus().toString());
            stmt.setString(6, input.getPaymentMethod().toString());
            stmt.setString(7, input.getCreditCardId());
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Taipei"));
            stmt.setTimestamp(8, input.getOrderTime(), calendar);
            if (input.getSeasoningDiscountCode() == 0) {
                stmt.setNull(9, NULL);
            } else {
                stmt.setInt(9, input.getSeasoningDiscountCode());
            }
            if (input.getShippingDiscountCode() == 0) {
                stmt.setNull(10, NULL);
            } else {
                stmt.setInt(10, input.getShippingDiscountCode());
            }

            stmt.executeUpdate();
            int orderId = getOrderId(connection);

            createOrderItems(connection, orderId, input.getOrderItems());

            output.setId(orderId);
            output.setOrderTime(input.getOrderTime());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private int getOrderId(Connection connection) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM `order` WHERE `id`=(SELECT max(`id`) FROM `order`);")) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    return Integer.parseInt(rs.getString("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("No order exist.");
    }

    private void createOrderItems(Connection connection, int orderId, Map<Integer, Integer> orderItems) {
        if (orderItems.isEmpty()) {
            return;
        }
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
                throw new RuntimeException(e);
            }
        });
    }
}
