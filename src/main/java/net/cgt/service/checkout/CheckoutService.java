package net.cgt.service.checkout;

import net.cgt.service.DB;
import net.cgt.service.item.Item;

import java.sql.*;
import java.util.Collections;
import java.util.Vector;

public class CheckoutService {
    public Connection db;

    public CheckoutService() {
        db = DB.connect();
    }

    public void addCheckoutItem(Item item) {
        String injection = "INSERT INTO checkout(number, code, name, price, quantity) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement statement = db.prepareStatement(injection, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, getCheckoutItems().size() + 1);
            statement.setString(2, item.getCode());
            statement.setString(3, item.getName());
            statement.setDouble(4, item.getPrice());
            statement.setInt(5, 1);

            statement.executeUpdate();
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public Vector<Vector<Object>> getCheckoutItems() {
        Vector<Vector<Object>> checkoutItems = new Vector<>();

        String injection = "SELECT * FROM checkout ORDER BY number ASC";

        try {
            PreparedStatement statement = db.prepareStatement(injection);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                Vector<Object> checklistItem = new Vector<>();

                String[] item = new String[5];

                item[0] = String.valueOf(results.getInt("number"));
                item[1] = results.getString("code");
                item[2] = results.getString("name");
                item[3] = String.valueOf(results.getDouble("price"));
                item[4] = String.valueOf(results.getInt("quantity"));

                Collections.addAll(checklistItem, item);

                checkoutItems.add(checklistItem);
            }

            return checkoutItems;
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
        return null;
    }

    public void updateCheckoutItemQuantity(Item item) {
        String selectInjection = "SELECT quantity FROM checkout WHERE code=?";

        try {
            PreparedStatement selectStatement = db.prepareStatement(selectInjection);
            selectStatement.setString(1, item.getCode());

            ResultSet result = selectStatement.executeQuery();

            if (result.next()) {
                String updateInjection = "UPDATE checkout SET quantity=? WHERE code=?";
                
                try {
                    PreparedStatement updateStatement = db.prepareStatement(updateInjection);
                    updateStatement.setInt(1, result.getInt("quantity") + 1);
                    updateStatement.setString(2, item.getCode());

                    updateStatement.executeUpdate();
                }
                catch (SQLException error) {
                    error.printStackTrace();
                }
            }
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void removeCheckoutItem(Item item) {
        String injection = "DELETE FROM checkout WHERE code=?";

        try {
            PreparedStatement statement = db.prepareStatement(injection);
            statement.setString(1, item.getCode());

            statement.executeUpdate();
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void removeAllCheckoutItems() {
        String injection = "TRUNCATE TABLE checkout";

        try {
            PreparedStatement statement = db.prepareStatement(injection);
            statement.executeLargeUpdate();
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
