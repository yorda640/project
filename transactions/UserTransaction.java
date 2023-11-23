package transactions;

import db.DBInstance;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.ShoppingCart;
import models.User;

public class UserTransaction {

    Connection connection = null;

    public boolean InsertUser(User user) {
        connection = DBInstance.getConnection();
        String insertQuery = "INSERT INTO User (name, email) VALUES ('" + user.Name + "', '" + user.Email + "')";
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                int rowsAffected = statement.executeUpdate(insertQuery);
                System.out.println("Inserted!");
                if (rowsAffected > 0) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public User getUserByEmail(String email) {
        String selectQuery = "SELECT * FROM User WHERE Email = '" + email+"'";
        User user = null;
        connection = DBInstance.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                user = new User();
                user.Id = resultSet.getInt("id");
                user.Name = resultSet.getString("name");
                user.Email = resultSet.getString("email");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }

        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        connection = DBInstance.getConnection();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                String selectQuery = "SELECT * FROM User";
                ResultSet resultSet = statement.executeQuery(selectQuery);

                while (resultSet.next()) {
                    User user = new User();
                    user.Id = resultSet.getInt("id");
                    user.Name = resultSet.getString("name");
                    user.Email = resultSet.getString("email");

                    userList.add(user);
                }

            } catch (SQLException e) {
                e.printStackTrace(); // Handle or log the exception as needed
            }
        }

        return userList;
    }
}
