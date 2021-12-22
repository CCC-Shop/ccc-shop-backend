package com.project.ccc_shop.order.usecase.update;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import com.project.ccc_shop.order.entity.Status;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.Calendar;
import java.util.TimeZone;

@Service
public class UpdateOrderUseCase implements UseCase<UpdateOrderInput, UpdateOrderOutput> {

    private MySQLDriver mySQLDriver;

    public UpdateOrderUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(UpdateOrderInput input, UpdateOrderOutput output) {
        try (Connection connection = this.mySQLDriver.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE `order` SET `status`= ? WHERE `id`= ?");

            stmt.setString(1, input.getStatus().toString());
            stmt.setInt(2, input.getOrderId());

            stmt.executeUpdate();

            updateTime(connection, input.getStatus(), input.getTime(), input.getOrderId());

            output.setTime(input.getTime());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void updateTime(Connection connection, Status status, Timestamp timestamp, int orderId) {
        if (status.equals(Status.SHIPPING)) {
            try (PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE `order` SET `shipping_time`= ? WHERE `id`= ?")) {
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Taipei"));
                stmt.setTimestamp(1, timestamp, calendar);
                stmt.setInt(2, orderId);

                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } else if (status.equals(Status.DELIVERED)) {
            try (PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE `order` SET `delivery_time`= ? WHERE `id`= ?")) {
                stmt.setTimestamp(1, timestamp);
                stmt.setInt(2, orderId);

                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
