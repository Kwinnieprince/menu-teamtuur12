package ucll.project.controller;

import ucll.project.domain.db.MenuRepositorySql;
import ucll.project.domain.db.UserRepository;
import ucll.project.domain.model.menu.Menu;
import ucll.project.domain.model.campus.Campus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MenuController extends BaseController {
    private MenuRepositorySql menuRepositorySql;

    public MenuController(UserRepository userRepository, Properties properties) {
        super(userRepository);
        menuRepositorySql = new MenuRepositorySql(properties);
    }

    public Menu getMenuOfTheDay(){
        return menuRepositorySql.getMenuOfTheDay();
    }

    public void  getMakeMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObject = new Date();
        String date;
        String prevCampus = Campus.PROXIMUS.toString();

        if (request.getParameter("campus") != null) {
            prevCampus = request.getParameter("campus");
            request.setAttribute("prevCampus", prevCampus);
        } else {
            request.setAttribute("prevCampus", Campus.PROXIMUS);
        }

        if (request.getParameter("date") != null) {
            date = request.getParameter("date");
            request.setAttribute("prevDate", date);
        } else {
            date = dateFormat.format(dateObject);
            request.setAttribute("prevDate", date);
        }

        ArrayList<Campus> campuses = new ArrayList<>(Arrays.asList(Campus.values()));
        campuses.remove(Campus.valueOf(prevCampus));

        request.setAttribute("prevDate", date);
        request.setAttribute("campuses", campuses);
        request.getRequestDispatcher("/makeMenu.jsp").forward(request, response);
    }
}
