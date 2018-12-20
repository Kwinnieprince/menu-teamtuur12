package ucll.project.domain.db;

import ucll.project.domain.DomainException;
import ucll.project.domain.model.dish.Category;
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
                    ("SELECT name, description, price_internal, price_external FROM \"menu-teamtuur12\".dish ");

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
                    ("INSERT INTO \"menu-teamtuur12\".dish (dish_name, dish_description, price_internal, price_external, category_id) VALUES (?, ? ,? ,?, ?);");
            statement.setString(1, dish.getName());
            statement.setString(2, dish.getDescription());
            statement.setDouble(3, dish.getInternalPrice());
            statement.setDouble(4, dish.getExternalPrice());
            statement.setInt(5, getCategoryId(dish.getCategory()));

            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCategoryId(String category) {
        int id = 0;
        try {
            connection = DriverManager.getConnection(properties.getProperty("url"), properties);
            PreparedStatement statement = connection.prepareStatement("SELECT category_id FROM \"menu-teamtuur12\".category WHERE category_name = ?");
            statement.setString(1, category);

            ResultSet set = statement.executeQuery();

            if(set.next()) {
                id = set.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public Dish getDish(int id) {
        Dish dish = new Dish();
        try {
            connection = DriverManager.getConnection(properties.getProperty("url"), properties);
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * from \"menu-teamtuur12\".dish WHERE dish_id=?;");
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
                    ("SELECT * from \"menu-teamtuur12\".dish WHERE dish_id=?;");
            statement.setString(1, name);
            ResultSet set = statement.executeQuery();

            dish = makeDish(set);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dish;
    }
}
