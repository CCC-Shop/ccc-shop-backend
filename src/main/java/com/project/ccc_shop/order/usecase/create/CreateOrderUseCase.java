package com.project.ccc_shop.order.usecase.create;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

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
                            "`shipping_discount_code`, " +
                            "`total_price`)" +
                            " VALUES (?,?,?,?,?,?,?,?,?,?,?)");

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
            stmt.setInt(11, input.getTotalPrice());

            stmt.executeUpdate();

            int orderId = getOrderId(connection);
            createOrderItems(connection, orderId, input.getOrderItems(), input.getCustomerId());
            output.setOrderTime(getOrderTime(connection, orderId));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private int getOrderId(Connection connection) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT `id` FROM `order` WHERE `id`=(SELECT max(`id`) FROM `order`)")) {

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        throw new RuntimeException("No order exist.");
    }

    private void createOrderItems(Connection connection, int orderId, Map<Integer, Integer> orderItems, int customerId) {
        if (orderItems.isEmpty()) {
            return;
        }
        List<Integer> productIdList = new ArrayList<>();
        orderItems.keySet().forEach(productId -> {
            productIdList.add(productId);
            try (PreparedStatement stmt = connection.prepareStatement(
                    "INSERT `order_items` (`order_id`, " +
                            "`product_id`, " +
                            "`quantity`)" +
                            " VALUES (?,?,?)")) {
                stmt.setInt(1, orderId);
                stmt.setInt(2, productId);
                stmt.setInt(3, orderItems.get(productId));

                stmt.executeUpdate();
                updateProductStock(connection, productId, orderItems.get(productId));
                deleteItemsInShoppingCart(connection, productId, customerId, orderItems.get(productId));
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
        generateOrderAndVenderRelation(connection, orderId, productIdList.get(0));
    }

    private void updateProductStock(Connection connection, int productId, int quantity) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "UPDATE `product` " +
                        "SET `stock`= `stock` - ? " +
                        "WHERE `id`=?")) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, productId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void deleteItemsInShoppingCart(Connection connection, int productId, int customerId, int quantity) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "DELETE FROM `shopping_cart` " +
                        "WHERE `product_id`=? " +
                        "AND `customer_id`=? " +
                        "AND `quantity`=?")) {
            stmt.setInt(1, productId);
            stmt.setInt(2, customerId);
            stmt.setInt(3, quantity);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void generateOrderAndVenderRelation(Connection connection, int orderId, Integer productId) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT `vender_id` FROM `product` WHERE `id`=?")) {
            stmt.setInt(1, productId);

            int venderId;
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    venderId = rs.getInt("vender_id");
                    insertValueToManageOrder(connection, orderId, venderId);
                } else {
                    throw new RuntimeException("product doesn't exist, where id = " + productId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void insertValueToManageOrder(Connection connection, int orderId, int venderId) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT `manage_order` (`order_id`, " +
                        "`vender_id`)" +
                        " VALUES (?,?)")) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, venderId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Timestamp getOrderTime(Connection connection, int orderId) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT `order_time` FROM `order` WHERE `id`=?")) {
            stmt.setInt(1, orderId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getTimestamp("order_time");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        throw new RuntimeException("order doesn't exist, where id = " + orderId);
    }
}
