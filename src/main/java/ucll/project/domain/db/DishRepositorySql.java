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

    private Dish makeDish(ResultSet result) throws SQLException {
        Dish dish = new Dish();
        if (result.next()) {
            String name = result.getString("name");
            String description = result.getString("description");
            double internalPrice = result.getDouble("price_internal");
            double externalPrice = result.getDouble("price_external");
            String category = result.getString("category");

            try {
                dish.setName(name);
                dish.setDescription(description);
                dish.setInternalPrice(internalPrice);
                dish.setExternalPrice(externalPrice);
                dish.setCategory(category);
            } catch (DomainException e) {
                e.printStackTrace();
            }
        }
        return dish;
    }

    public ArrayList<Dish> getAllDish() {
        ArrayList<Dish> dishes = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(properties.getProperty("url"), properties);
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT name, description, price_internal, price_external FROM \"menu-teamtuur12\".Dish ");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                dishes.add(makeDish(resultSet));
            }


            } catch (SQLException e) {
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

    public Dish getDish(int id) {
        Dish dish = new Dish();
        try {
            connection = DriverManager.getConnection(properties.getProperty("url"), properties);
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * from \"menu-teamtuur12\".Dish WHERE dish_id=?;");
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();

            dish = makeDish(set);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dish;
    }

    public Dish getDish(String name) {
        Dish dish = new Dish();
        try {
            connection = DriverManager.getConnection(properties.getProperty("url"), properties);
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * from \"menu-teamtuur12\".Dish WHERE dish_id=?;");
            statement.setString(1, name);
            ResultSet set = statement.executeQuery();

            dish = makeDish(set);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dish;
    }
}
