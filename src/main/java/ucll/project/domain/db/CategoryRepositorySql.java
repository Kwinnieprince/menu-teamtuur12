package ucll.project.domain.db;

import ucll.project.domain.DomainException;
import ucll.project.domain.model.dish.Category;
import ucll.project.domain.db.DbException;
import ucll.project.domain.model.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CategoryRepositorySql implements CategoryRepository {
    private final Properties properties;
    private final String url;

    public CategoryRepositorySql(Properties properties) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.properties = properties;
        url = properties.getProperty("url");
    }

    @Override
    public void createCategory(Category category) {
        try (Connection connection = DriverManager.getConnection(url, properties)){
            PreparedStatement statement = connection.prepareStatement("insert into \"menu-teamtuur12\".category (id, category_name, category_description) values (? , ?)");

            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());

            statement.execute();

            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Category get(String categoryName) {
        Category category = new Category();

        try (Connection connection = DriverManager.getConnection(url, properties)){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"menu-teamtuur12\".category WHERE category_name = ?");
            statement.setString(1, categoryName);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                category = makeCategory(set);
            }

            statement.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return category;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"menu-teamtuur12\".category")){

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Category c = makeCategory(set);
                categories.add(c);
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }

    @Override
    public void update(Category category) {
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement("UPDATE \"menu-teamtuur12\".category set category_name=?, category_description=?")){

            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Category category) {
        try (Connection connection = DriverManager.getConnection(url, properties);
        PreparedStatement statement = connection.prepareStatement("DELETE * FROM \"menu-teamtuur12\".category WHERE category_name = ?")){
            statement.setString(1, category.getName());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Category makeCategory(ResultSet set) {
        Category c = new Category();

        try {
            if (set.next()) {
                c.setName(set.getString("category_name"));
                c.setDescription(set.getString("category_description"));
            }
        } catch (SQLException e) {
            e.getErrorCode();
        } catch (DomainException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }
}
