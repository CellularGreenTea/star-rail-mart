package net.cgt.service.db;

import net.cgt.service.DB;
import net.cgt.service.item.Item;

import java.sql.*;
import java.util.ArrayList;

public class DBService {
    public Connection db;

    public DBService() {
        db = DB.connect();
    }

    public void addItem(Item item) {
        String injection = "INSERT INTO items(code, name, price, stock) VALUES(?, ?, ?, ?)";

        try {
            PreparedStatement statement = db.prepareStatement(injection, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, item.getCode());
            statement.setString(2, item.getName());
            statement.setDouble(3, item.getPrice());
            statement.setInt(4, item.getStock());

            statement.executeUpdate();
            
        } catch (SQLException error) {
            error.printStackTrace();
        }
        
    }

    public ArrayList<Item> getItemsByCode(String code) {
        ArrayList<Item> categoryItems = new ArrayList<>();

        code = Character.toString(code.charAt(0)).toUpperCase();
        
        String injection = "SELECT code, name, price, stock FROM items WHERE code LIKE ? ESCAPE '\\'";

        try {
            PreparedStatement statement = db.prepareStatement(injection);
            statement.setString(1, code + "%");

            ResultSet results = statement.executeQuery();

            while (results.next()) {
                Item item = new Item(results.getString("code"), results.getString("name"), results.getDouble("price"), results.getInt("stock"));

                categoryItems.add(item);
            }

            categoryItems.sort(new ItemsComparator());

            return categoryItems;
        }

        catch (SQLException error) {
            error.printStackTrace();
            return null;
        }
    }

    public ArrayList<Item> getAllItems() {
        ArrayList<Item> items = new ArrayList<>();

        String injection = "SELECT * FROM items ORDER BY code ASC";

        try {
            PreparedStatement statement = db.prepareStatement(injection);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                Item item = new Item(results.getString("code"), results.getString("name"), results.getDouble("price"), results.getInt("stock"));
                items.add(item);
            }

            items.sort(new ItemsComparator());

            return items;
        }

        catch (SQLException error) {
            error.printStackTrace();
            return null;
        }
    }

    public ArrayList<Item> getItemsBySearchQuery(String query) {
        ArrayList<Item> filteredItems = new ArrayList<>();

        String injection = "SELECT code, name, price, stock FROM items WHERE UPPER(name) LIKE ? ESCAPE '\\'";

        try {
            PreparedStatement statement = db.prepareStatement(injection);
            statement.setString(1, "%" + query.toUpperCase() + "%");

            ResultSet results = statement.executeQuery();

            while (results.next()) {
                Item item = new Item(results.getString("code"), results.getString("name"), results.getDouble("price"), results.getInt("stock"));

                filteredItems.add(item);
            }

            filteredItems.sort(new ItemsComparator());

            return filteredItems;
        }

        catch (SQLException error) {
            error.printStackTrace();
            return null;
        }
    }

    public ArrayList<Item> getItemsFromCategoryBySearchQuery(String code, String query) {
        ArrayList<Item> filteredCategoryItems = new ArrayList<>();

        code = Character.toString(code.charAt(0)).toUpperCase();
        
        String injection = "SELECT code, name, price, stock FROM items WHERE code LIKE ? AND UPPER(name) LIKE ? ESCAPE '\\'";

        try {
            PreparedStatement statement = db.prepareStatement(injection);
            statement.setString(1, code + "%");
            statement.setString(2, "%" + query.toUpperCase() + "%");

            ResultSet results = statement.executeQuery();

            while (results.next()) {
                Item item = new Item(results.getString("code"), results.getString("name"), results.getDouble("price"), results.getInt("stock"));

                filteredCategoryItems.add(item);
            }

            filteredCategoryItems.sort(new ItemsComparator());

            return filteredCategoryItems;
        }

        catch (SQLException error) {
            error.printStackTrace();
            return null;
        }
    }

    public void updateItemStock(Item item, int newStock) {
        String injection = "UPDATE items SET name=?, price=?, stock=? WHERE code=?";

        try {
            PreparedStatement statement = db.prepareStatement(injection);
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setInt(3, newStock);
            statement.setString(4, item.getCode());

            statement.executeUpdate();
        }

        catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void removeItem(Item item) {
        String injection = "DELETE FROM items WHERE code=?";

        try {
            PreparedStatement statement = db.prepareStatement(injection);
            statement.setString(1, item.getCode());

            statement.executeUpdate();
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
