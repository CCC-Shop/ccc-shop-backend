package com.project.ccc_shop.report.usecase.productSalesReport;

import com.project.ccc_shop.common.MySQLDriver;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetProductSalesReportUseCase {
    private MySQLDriver mySQLDriver;

    public GetProductSalesReportUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    public void execute(GetProductSalesReportInput input, GetProductSalesReportOutput output) {
        List<String> categoryList = new ArrayList<>();
        List<Integer> quantityList = new ArrayList<>();

        try (Connection connection = this.mySQLDriver.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT p.`name` AS product_name, SUM(oi.quantity) AS product_quantity " +
                            "FROM `manage_order` AS `m`, `order_items` AS `oi`, `product` AS `p`, `order` AS `o` " +
                            "WHERE m.vender_id = ? AND m.order_id = o.id AND o.order_time > ? AND o.order_time < ? " +
                            "AND oi.order_id = o.id AND oi.product_id = p.id " +
                            "GROUP BY p.`name`;");

//            Timestamp startTime = Timestamp.from(Instant.parse(input.getStartTime()));
//            Timestamp endTime = Timestamp.from(Instant.parse(input.getEndTime()));
//            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Taipei"));  // set timezone
            stmt.setInt(1, input.getVenderId());
            stmt.setTimestamp(2, input.getStartTime());
            stmt.setTimestamp(3, input.getEndTime());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    categoryList.add(rs.getString("product_name"));
                    quantityList.add(rs.getInt("product_quantity"));
                }
            }

            output.setCategoryList(categoryList);
            output.setQuantityList(quantityList);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
