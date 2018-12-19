package ucll.project.domain.db;

import ucll.project.domain.DomainException;
import ucll.project.domain.model.dish.Dish;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DishRepositorySql {

    private final Properties properties;
    private Connection connection;

    public DishRepositorySql(Properties properties) {
        this.properties = properties;
    }

    public ArrayList<Dish> getAllDish() {
        ArrayList<Dish> dishes = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(properties.getProperty("url"), properties);
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT name, description, price_internal, price_external FROM \"menu-teamtuur12\".Dish ");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double internalPrice = resultSet.getDouble("price_internal");
                double externalPrice = resultSet.getDouble("price_external");
                String category = resultSet.getString("category");

                dishes.add(new Dish(name, description, internalPrice, externalPrice, category));
            }
        } catch (SQLException | DomainException e) {
            e.printStackTrace();
        }

        return dishes;
    }

    public void addDish(Dish dish) {
        try {
            connection = DriverManager.getConnection(properties.getProperty("url"), properties);
            PreparedStatement statement = connection.prepareStatement
                    ("INSERT INTO \"menu-teamtuur12\".Dish (name, description, price_internal, price_external, category) VALUES (?, ? ,? ,?, ?);");
            statement.setString(1, dish.getName());
            statement.setString(2, dish.getDescription());
            statement.setDouble(3, dish.getInternalPrice());
            statement.setDouble(4, dish.getExternalPrice());
            statement.setString(5, dish.getCategory());

            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
