package ucll.project.domain.db;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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
         this.url = properties.getProperty("url");
    }

    public Menu getMenuOfTheDay(){
        Date date_db;
        String menu_name;
        int dish_id;
        String text;
        double price_internal;
        double price_external;
        String description;
        int category_id;
        String dish_name;
        String description_category;
        String category_name;


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Menu menu = new Menu();
        Date dateDate = new Date();
        String date = "'" + dateFormat.format(dateDate) + "'";
        try (Connection connection = DriverManager.getConnection(url, properties)){
            PreparedStatement statement = connection.prepareStatement("select * from \"menu-teamtuur12\".menu inner join \"menu-teamtuur12\".dish_has_menu using (menu_id) inner join \"menu-teamtuur12\".dish using(dish_id)  inner join \"menu-teamtuur12\".category using(category_id) where date = ?::date");
            statement.setString(1, date);
            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet.toString());
            while (resultSet.next()){
                date_db = resultSet.getDate("date");
                menu_name = resultSet.getString("menu_name");
                description = resultSet.getString("dish_description");
                dish_name = resultSet.getString("dish_name");
                price_external = resultSet.getDouble("price_external");
                price_internal = resultSet.getDouble("price_internal");
                category_name = resultSet.getString("category_name");
                description_category = resultSet.getString("category_description");
                Dish dish = new Dish();
                dish.setCategory(description_category);
                dish.setDescription(description);
                dish.setExternalPrice(price_external);
                dish.setInternalPrice(price_internal);
                dish.setName(dish_name);
                dish.setCategoryDescription(category_name);
                System.out.println(dish.getCategory() + "HALP");
                menu.addDish(dish);
                menu.setDate(date_db);
                menu.setMenuName(menu_name);
                System.out.println(menu.getDate().toString() + "HILP");
            }
        } catch (SQLException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch (DomainException e){
            throw new IllegalArgumentException("menu error");
        }
        return menu;
    }
}
