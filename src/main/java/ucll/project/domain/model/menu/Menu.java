package ucll.project.domain.model.menu;


import ucll.project.domain.model.dish.Dish;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private Date date;
    private List<Dish>dishes = new ArrayList<>();
    private String menuName;

    public Menu(Date date, Dish dish){
        setDate(date);
    }

    public Menu(){

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void addDish(Dish dish){
        if (dish == null){
            throw new IllegalArgumentException("No dish to add");
        }else {
            dishes.add(dish);
        }
    }
    
    public void setMenuName(String name){
        this.menuName = name;
    }
}
