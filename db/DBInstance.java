package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBInstance {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/mydb";
            String userName = "root";
            String password = "@Miu#2023";

            return DriverManager.getConnection(jdbcUrl, userName, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            //System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }
}
