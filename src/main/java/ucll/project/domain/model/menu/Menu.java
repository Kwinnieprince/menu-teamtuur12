package ucll.project.domain.model.menu;


import ucll.project.domain.model.dish.Dish;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private Date date;
    private List<Dish>dishes = new ArrayList<>();
    private String menuName;
    String dateString;

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
        setWeekday(date);
    }

    public void setWeekday(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, date.getYear());
        calendar.set(Calendar.DAY_OF_YEAR, date.getDay());
        int weekday = calendar.get(Calendar.DAY_OF_WEEK);
        DateFormatSymbols dfs = new DateFormatSymbols();
        dateString = dfs.getWeekdays()[weekday];
    }

    public String getWeekday(){
        return dateString;
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

    public List<Dish> getDishes(){
        return dishes;
    }

    public String getMenuName(){
        return menuName;
    }
    
    public void setMenuName(String name){
        this.menuName = name;
    }
}
