package ucll.project.domain.db;

import org.w3c.dom.DOMException;
import ucll.project.domain.DomainException;
import ucll.project.domain.model.dish.Dish;
import ucll.project.domain.model.menu.Menu;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class MenuRepositorySql {
    private Properties properties;
    private String url;

    public MenuRepositorySql(Properties properties){
         this.properties = properties;
    }

    public Menu getMenuOfTheDay(){
        int menu_id;
        Date date_db;
        String menu_name;
        int dish_id;
        String text;
        double price_student;
        double price_extern;
        String description;
        int category_id;
        String name;
        String description_category;


        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
        Date dateDate = new Date();
        String date = dateFormat.format(dateDate);
        try (Connection connection = DriverManager.getConnection(url, properties)){
            PreparedStatement statement = connection.prepareStatement("select * from \"menu-teamtuur12\".menu inner join \"menu-teamtuur12\".menu_has_dishes using (menu_id) inner join \"menu-teamtuur12\".dish using(dish_id)  inner join \"menu-teamtuur12\".category using(category_id) where date = ?");
            statement.setString(1, date);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                menu_id = resultSet.getInt("menu_id");
                date_db = resultSet.getDate("date");
                menu_name = resultSet.getString("menu_name");
                dish_id = resultSet.getInt("dish_id");
                description = resultSet.getString("description");
                name = resultSet.getString("name");
                price_extern = resultSet.getDouble("price_external");
                price_student = resultSet.getDouble("price_student");
                category_id = resultSet.getInt("category_id");
                name = resultSet.getString("name");
                description_category = resultSet.getString("description_category");
                Dish dish = new Dish();
                dish.setCategory(description_category);
                dish.setDescription(description);
            }
        } catch (SQLException e){

        }catch (DomainException e){

        }
        return null;
    }
}
