package ucll.project.controller;

import com.sun.deploy.net.HttpResponse;
import ucll.project.domain.model.campus.Campus;
import ucll.project.domain.model.dish.Category;
import ucll.project.domain.model.user.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MenuController extends BaseController {

    public MenuController(UserRepository userRepository) {
        super(userRepository);
    }

    public void  getMakeMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("campuses", Campus.values());
        request.getRequestDispatcher("/makeMenu.jsp").forward(request, response);
    }
}
