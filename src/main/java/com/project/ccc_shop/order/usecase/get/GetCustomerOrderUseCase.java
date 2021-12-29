package com.project.ccc_shop.order.usecase.get;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import com.project.ccc_shop.order.entity.Order;
import com.project.ccc_shop.order.entity.Payment;
import com.project.ccc_shop.order.entity.Status;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetCustomerOrderUseCase implements UseCase<GetCustomerOrderInput, GetCustomerOrderOutput> {
    private MySQLDriver mySQLDriver;

    public GetCustomerOrderUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(GetCustomerOrderInput input, GetCustomerOrderOutput output) {
        try (Connection connection = this.mySQLDriver.getConnection()) {
            List<Order> orderList = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM `order` WHERE `customer_id`=?;");
            stmt.setInt(1, input.getCustomerId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("id"));
                    order.setCustomerId(rs.getInt("customer_id"));
                    order.setShippingFee(rs.getInt("shipping_fee"));
                    order.setRecipientName(rs.getString("recipient_name"));
                    order.setShippingAddress(rs.getString("shipping_address"));
                    order.setStatus(Status.valueOf(rs.getString("status")));
                    order.setPaymentMethod(Payment.valueOf(rs.getString("payment_method")));
                    order.setCreditCardId(rs.getString("credit_card_id"));
                    order.setOrderTime(rs.getTimestamp("order_time"));
                    order.setShippingTime(rs.getTimestamp("shipping_time"));
                    order.setDeliveryTime(rs.getTimestamp("delivery_time"));
                    order.setSeasoningDiscountCode(rs.getInt("seasoning_discount_code"));
                    order.setShippingDiscountCode(rs.getInt("shipping_discount_code"));
                    order.setTotalPrice(rs.getInt("total_price"));
                    addOrderItems(connection, order);

                    orderList.add(order);
                }
            }

            output.setOrderList(orderList);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void addOrderItems(Connection connection, Order order) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM `order_items` WHERE `order_id` = ?")) {
            stmt.setInt(1, order.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    order.putOrderItem(getProductName(connection, rs.getInt("product_id")), rs.getInt("quantity"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private String getProductName(Connection connection, int productId) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT `name` FROM `product` WHERE `id` = ?")) {
            stmt.setInt(1, productId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    return rs.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        throw new RuntimeException("product not found");
    }
}
