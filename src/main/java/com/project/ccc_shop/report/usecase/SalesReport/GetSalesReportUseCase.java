package com.project.ccc_shop.report.usecase.SalesReport;

import com.project.ccc_shop.common.MySQLDriver;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@Service
public class GetSalesReportUseCase {
    private MySQLDriver mySQLDriver;

    public GetSalesReportUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    public void execute(GetSalesReportInput input, GetSalesReportOutput output) {
        List<Timestamp> originalTimeList = new ArrayList<>();
        List<String> timeStringList = new ArrayList<>();
        List<Integer> originalTotalPriceList = new ArrayList<>();
        List<Integer> totalPriceList = new ArrayList<>();

        try (Connection connection = this.mySQLDriver.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT o.order_time, o.total_price " +
                            "FROM `manage_order` AS `mo`LEFT JOIN `order` AS `o` ON `mo`.`order_id` = `o`.id " +
                            "WHERE mo.vender_id = ? " +
                            "ORDER BY o.order_time;");

            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Taipei"));
            stmt.setInt(1, input.getVenderId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    originalTimeList.add(rs.getTimestamp("o.order_time", calendar));
                    originalTotalPriceList.add(rs.getInt("o.total_price"));
                }
            }

            calendar.setTime(new Date());
            calendar.set(Calendar.DATE, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date now = calendar.getTime();

            calendar.add(Calendar.YEAR, -1);
            calendar.add(Calendar.MONTH, 1);
            Date lastYear = calendar.getTime();  // If it's 2022/01/XX now, it will get 2021/02/01.
            System.out.println(lastYear);

            Timestamp timeRecord = null;
            Date currentMonth = calendar.getTime();
            calendar.add(Calendar.MONTH, 1);
            Date monthStamp = calendar.getTime();
            timeStringList.add(currentMonth.toString());
            totalPriceList.add(0);
            int index = -1;

            for (Timestamp t : originalTimeList){
                index++;
                if (t.before(lastYear)){
                    continue;
                }
                while(!t.before(monthStamp)){
                    currentMonth = monthStamp;
                    calendar.add(Calendar.MONTH, 1);
                    monthStamp = calendar.getTime();
                    timeStringList.add(currentMonth.toString());
                    totalPriceList.add(0);
                }
                totalPriceList.set(totalPriceList.size()-1, totalPriceList.get(totalPriceList.size()-1) + originalTotalPriceList.get(index));
            }

            while (currentMonth.before(now)){ // Consideration for the last several months that don't have any data
                currentMonth = calendar.getTime();
                timeStringList.add(currentMonth.toString());
                totalPriceList.add(0);
                calendar.add(Calendar.MONTH, 1);
            }

            output.setTimeList(timeStringList);
            output.setTotalPriceList(totalPriceList);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
