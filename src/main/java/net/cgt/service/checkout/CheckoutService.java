package net.cgt.service.checkout;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.cgt.service.DB;
import net.cgt.service.item.Item;

public class CheckoutService {
    public Connection db;

    public CheckoutService() {
        db = DB.connect();
    }

    public void addCheckoutItem(Item item) {
        String injection = "INSERT INTO checkout(code, name, price, quantity) VALUES(?,?,?,?)";

        try {
            PreparedStatement statement = db.prepareStatement(injection, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getCode());
            statement.setString(2, item.getName());
            statement.setDouble(3, item.getPrice());
            statement.setInt(4, 1);

            statement.executeUpdate();
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
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
