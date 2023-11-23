package transactions;

import db.DBInstance;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import models.Item;
import java.util.ArrayList;
import java.sql.SQLException;
import models.*;

public class ShoppingCartTransaction {

    Connection connection = null;

    public ShoppingCart selectShoppingCart(int userId) {
        String selectQuery = "SELECT * FROM ShoppingCart WHERE UserId = " + userId;
        ShoppingCart shoppingCart = null;
        connection = DBInstance.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                shoppingCart = new ShoppingCart();
                shoppingCart.Id = resultSet.getInt("id");
                shoppingCart.UserId = resultSet.getInt("userId");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }

        return shoppingCart;
    }
    
    public boolean createShoppingCart(int userId) {            
        String insertQuery = "INSERT INTO ShoppingCart (UserId) VALUES (" + userId + ")";

        connection = DBInstance.getConnection();
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

    public boolean insertItemToShoppingCart(int userId, int itemId) {
        ShoppingCart shoppingCart = selectShoppingCart(userId);
        if(shoppingCart == null){
            createShoppingCart(userId);
            shoppingCart = selectShoppingCart(userId);
        }
        
        String insertQuery = "INSERT INTO ShoppingCartItem (ShoppingCartId, ItemId) VALUES (" + shoppingCart.Id + ", " + itemId + ")";

        connection = DBInstance.getConnection();
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


    public boolean purchaseShoppingCartItems(int userId){

        List<Item> items = getShoppingCartItems(userId);
        ItemTransaction transaction = new ItemTransaction();

        for(Item item : items){

            if(item.getStockQuantity() > 1)
            {
                item.setStockQuantity(item.getStockQuantity() - 1);
                transaction.updateItem(item);
                deleteShoppingCartItem(userId, item.getId());
            }
            else if(item.getStockQuantity() == 1){
                deleteShoppingCartItem(userId, item.getId());
                transaction.deleteItem(item);
            }
            else {
                System.out.println("Item Out of Stock");
            }
        }

        return true;
    }

    public boolean deleteShoppingCartItem(int userId, int itemId){
        ShoppingCart shoppingCart = selectShoppingCart(userId);

        connection = DBInstance.getConnection();

        String deleteQuery = "DELETE FROM shoppingcartitem WHERE ShoppingCartId = " + shoppingCart.Id +
                " AND ItemId = " + itemId;

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                int rowsAffected = statement.executeUpdate(deleteQuery);
                if (rowsAffected > 0) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public List<Item> getShoppingCartItems(int userId) {
        connection = DBInstance.getConnection();

        String selectQuery = "SELECT Item.Id, Item.Name, Item.Description, Item.Price, Item.StockQuantity "
                + "FROM ShoppingCartItem "
                + "JOIN Item ON ShoppingCartItem.ItemId = Item.Id "
                + "JOIN ShoppingCart ON ShoppingCartItem.ShoppingCartId = ShoppingCart.Id "
                + "WHERE ShoppingCart.UserId = " + userId;

        List<Item> itemList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            // Process the result set and build the list of items
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                double price = resultSet.getDouble("Price");
                int stockQuantity = resultSet.getInt("StockQuantity");

                Item item = new Item(id, name, price, description, stockQuantity);
                itemList.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }

        return itemList;
    }
}
