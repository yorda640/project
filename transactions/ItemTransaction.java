package transactions;

import models.Item;
import db.DBInstance;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemTransaction {

    public boolean updateItem(Item item) {
        Connection connection = DBInstance.getConnection();
        String insertQuery = "UPDATE Item SET name = '" + item.getName() + "' ,description = '" + item.getDescription() +
            "' ,price = " + item.getPrice() + " ,stockQuantity = " + item.getStockQuantity() +
                " WHERE id = " + item.getId();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                int rowsAffected = statement.executeUpdate(insertQuery);
                if (rowsAffected > 0) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean deleteItem(Item item) {
        Connection connection = DBInstance.getConnection();
        //String deleteShoppingCartITem = "DELETE FROM shoppingcartitem WHERE ItemId = " + item.getId();
        String deleteQuery = "DELETE FROM item WHERE id = " + item.getId();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                //boolean rowsAffected = statement.execute(deleteShoppingCartITem);
                boolean rowsAffected = statement.execute(deleteQuery);
                return rowsAffected;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean InsertItem(Item item) {
        Connection connection = DBInstance.getConnection();
        String insertQuery = "INSERT INTO Item (name, description, price, stockQuantity) VALUES ('" + item.Name + "', '" + item.Description + "', " + item.Price + ", " + item.StockQuantity + ")";
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

    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        Connection connection = DBInstance.getConnection();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                String selectQuery = "SELECT * FROM Item";
                ResultSet resultSet = statement.executeQuery(selectQuery);

                while (resultSet.next()) {
                    Item item = new Item();
                    item.Id = resultSet.getInt("id");
                    item.Name = resultSet.getString("name");
                    item.Description = resultSet.getString("description");
                    item.Price = resultSet.getDouble("price");
                    item.StockQuantity = resultSet.getInt("stockQuantity");

                    itemList.add(item);
                }

            } catch (SQLException e) {
                e.printStackTrace(); // Handle or log the exception as needed
            }
        }

        return itemList;
    }
}
