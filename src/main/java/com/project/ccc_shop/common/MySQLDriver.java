package com.project.ccc_shop.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDriver {
    private final String url;
    private final String user;
    private final String password;
    private final String driver;

    public MySQLDriver() {
        this.url = "jdbc:mariadb://127.0.0.1:3306/ccc_shop" +
                "?useUnicode=true" +
                "&useJDBCCompliantTimezoneShift=true" +
                "&useLegacyDatetimeCode=false" +
                "&serverTimezone=UTC" +
                "&characterEncoding=UTF-8" +
                "&createDatabaseIfNotExist=true";
//        this.url = "jdbc:mariadb://localhost:3306/ccc_shop";
        this.user = "root";
        this.password = "root";
        this.driver = "org.mariadb.jdbc.Driver";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url,this.user,this.password);
    }

    public void closeConnection(Connection connection) {
        if (connection == null) return;
        try {
            connection.close();
        } catch (SQLException e) {
            /* No connection */
        }
    }
}
