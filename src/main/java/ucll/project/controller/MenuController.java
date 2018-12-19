package ucll.project.controller;

import ucll.project.domain.db.MenuRepositorySql;
import ucll.project.domain.model.menu.Menu;
import ucll.project.domain.model.user.UserRepository;
import ucll.project.domain.model.campus.Campus;
import ucll.project.domain.model.user.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MenuController extends BaseController {
    private MenuRepositorySql menuRepositorySql;

    public MenuController(UserRepository userRepository) {
        super(userRepository);
        menuRepositorySql = new MenuRepositorySql(properties);
    }

    public Menu getMenuOfTheDay(){
        return menuRepositorySql.getMenuOfTheDay();
    }

    public void  getMakeMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("campuses", Campus.values());
        request.getRequestDispatcher("/makeMenu.jsp").forward(request, response);
    }
}
