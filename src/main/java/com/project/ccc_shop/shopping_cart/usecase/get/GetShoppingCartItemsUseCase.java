package com.project.ccc_shop.shopping_cart.usecase.get;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import com.project.ccc_shop.product.entity.Product;
import com.project.ccc_shop.product.usecase.get.GetProductInput;
import com.project.ccc_shop.product.usecase.get.GetProductOutput;
import com.project.ccc_shop.shopping_cart.entity.Item;
import com.project.ccc_shop.shopping_cart.entity.ShoppingCartItemsForOneVender;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetShoppingCartItemsUseCase implements UseCase<GetShoppingCartItemsInput, GetShoppingCartItemsOutput> {

    private MySQLDriver mySQLDriver;

    public GetShoppingCartItemsUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(GetShoppingCartItemsInput input, GetShoppingCartItemsOutput output) {

        try (Connection connection = this.mySQLDriver.getConnection()) {

            List<ShoppingCartItemsForOneVender> shoppingCartItems = new ArrayList<>();
            List<Item> itemList = new ArrayList<>();
            int lastVenderId = 0;
            String lastVenderName = "";
            int rowAmount;

            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT `v`.`username`, sc.quantity, `p`.* " +
                            "FROM ((`shopping_cart` AS sc LEFT OUTER JOIN `product` AS p ON p.id = sc.product_id) LEFT OUTER JOIN `user` AS v ON v.id = p.vender_id) " +
                            "WHERE sc.customer_id = ? " +
                            "ORDER BY p.vender_id;");

            stmt.setInt(1, input.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                rs.last();
                rowAmount = rs.getRow();
                rs.beforeFirst();
                while (rs.next()) {

                    int id = rs.getInt("id");
                    int venderId = rs.getInt("vender_id");
                    String name = rs.getString("name");
                    String category = rs.getString("category");
                    int price = rs.getInt("price");
                    int stock = rs.getInt("stock");
                    String warehouseAddress = rs.getString("warehouse_address");
                    String description = rs.getString("description");
                    String pictureURL = rs.getString("pictureURL");
                    String venderName = rs.getString("username");
                    int quantity = rs.getInt("quantity");

                    Item item = new Item(id, venderId, name, category, price, stock, warehouseAddress, description, pictureURL, venderName, quantity);

                    if (rs.getRow() == 1) {
                        lastVenderId = venderId;
                        lastVenderName = venderName;
                        itemList.add(item);
                    } else if (lastVenderId == venderId) {
                        itemList.add(item);
                    } else {
                        ShoppingCartItemsForOneVender shoppingCartItemsForOneVender = new ShoppingCartItemsForOneVender(lastVenderName, itemList);
                        shoppingCartItems.add(shoppingCartItemsForOneVender);
                        itemList = new ArrayList<>();
                        lastVenderId = venderId;
                        lastVenderName = venderName;
                        itemList.add(item);
                    }
                    if (rs.getRow() == rowAmount) {
                        ShoppingCartItemsForOneVender shoppingCartItemsForOneVender = new ShoppingCartItemsForOneVender(venderName, itemList);
                        shoppingCartItems.add(shoppingCartItemsForOneVender);
                    }
                }
            }
            output.setShoppingCartItems(shoppingCartItems);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
