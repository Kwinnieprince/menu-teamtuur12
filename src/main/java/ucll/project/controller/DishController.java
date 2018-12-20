package ucll.project.controller;

import ucll.project.domain.db.DishRepositorySql;
import ucll.project.domain.model.dish.Category;
import ucll.project.domain.model.dish.Dish;
import ucll.project.domain.db.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class DishController extends BaseController {
    private final DishRepositorySql dishRepositorySql;
    private final String validPattern = "[a-zA-Z_0-9 ]+";

    public DishController(UserRepository userRepository, DishRepositorySql dishRepositorySql) {
        super(userRepository);
        this.dishRepositorySql = dishRepositorySql;
    }

    public void  getAddDish(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //todo change enum to database category values
        request.setAttribute("categories", Category.values());
        request.getRequestDispatcher("/addDish.jsp").forward(request, response);
    }

    public void postAddDish(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> errors = new ArrayList<String>();

        Dish dish;

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double internalPrice = Double.parseDouble(request.getParameter("internalPrice"));
        double externalPrice = Double.parseDouble(request.getParameter("externalPrice"));
        String category = request.getParameter("category");

        validateName(name, errors);
        validateDescription(description, errors);
        validateInternalPrice(internalPrice, errors);
        validateExternalPrice(externalPrice, errors);
        validateCategory(category, errors);

        if (errors.size() == 0) {
            try {
                dish = new Dish(name, description, internalPrice, externalPrice, category);
                dishRepositorySql.addDish(dish);
                request.getRequestDispatcher("/addDish.jsp").forward(request, response);
            } catch (Exception e) {
                errors.add(e.getMessage());
            }
        }
            request.setAttribute("name", request.getParameter("name"));
            request.setAttribute("description", request.getParameter("description"));
            request.setAttribute("internalPrice", request.getParameter("internalPrice"));
            request.setAttribute("externalPrice", request.getParameter("externalPrice"));
            request.setAttribute("category", request.getParameter("category"));
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/addDish.jsp").forward(request, response);
    }

    private void validateName(String name, ArrayList<String> errors){
        if (name == null) {
            errors.add("name is null");
        } else if (name.isEmpty()) {
            errors.add("name is empty");
        } else if (!name.matches(validPattern)) {
            errors.add("invalid characters");
        }
    }

    private void validateDescription (String description, ArrayList <String> errors){
        if (description == null) {
            errors.add("description is null");
        } else if (description.isEmpty()) {
            errors.add("description is empty");
        } else if (!description.matches(validPattern)) {
            errors.add("invalid characters");
        }
    }

    private void validateInternalPrice(double internalPrice, ArrayList<String> errors){
        if (internalPrice <= 0) {
            errors.add("internal price cannot be smaller or equal to 0");
        }
    }

    private void validateExternalPrice (double externalPrice, ArrayList <String> errors){
        if (externalPrice <= 0) {
            errors.add("external price cannot be smaller or equal to 0");
        }
    }

    //todo check if category equals categories in database.
    private void validateCategory (String category, ArrayList <String> errors){
        if (category == null) {
            errors.add("category is empty");
        } else if (category.isEmpty()) {
            errors.add("category is empty");
        } else if (!category.matches(validPattern)) {
            errors.add("invalid characters");
        }
    }
}
