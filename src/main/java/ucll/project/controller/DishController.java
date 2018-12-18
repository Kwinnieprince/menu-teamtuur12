package ucll.project.controller;

import ucll.project.domain.model.dish.Category;
import ucll.project.domain.model.user.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class DishController extends BaseController {

    public DishController(UserRepository userRepository) {
        super(userRepository);
    }

    public void  getAddDish(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categories", Category.values());
        request.getRequestDispatcher("/addDish.jsp").forward(request, response);
    }

    public void postAddDish(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("name", request.getParameter("name"));
        request.getRequestDispatcher("/addDish.jsp").forward(request, response);
    }
}
