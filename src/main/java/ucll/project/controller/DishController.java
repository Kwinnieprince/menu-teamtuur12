package ucll.project.controller;

import ucll.project.domain.model.user.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DishController extends BaseController {

    public DishController(UserRepository userRepository) {
        super(userRepository);
    }
    //todo review exception
    public void  getAddDish(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/addDish.jsp").forward(request, response);
    }

    public void postAddDish(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("name", request.getAttribute("name"));
        request.getRequestDispatcher("/addDish.jsp").forward(request, response);
    }
}
