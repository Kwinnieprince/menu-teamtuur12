package ucll.project.controller;

import ucll.project.domain.db.MenuRepositorySql;
import ucll.project.domain.db.UserRepository;
import ucll.project.domain.model.menu.Menu;

import java.util.Properties;

public class MenuController extends BaseController {
    private MenuRepositorySql menuRepositorySql;


    public MenuController(UserRepository userRepository, Properties properties) {
        super(userRepository);
        menuRepositorySql = new MenuRepositorySql(properties);
    }

    public Menu getMenuOfTheDay(){
        return menuRepositorySql.getMenuOfTheDay();
    }

}
