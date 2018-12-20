package ucll.project.controller;

import ucll.project.domain.db.CategoryRepositorySql;
import ucll.project.domain.db.DishRepositorySql;
import ucll.project.domain.model.dish.Category;
import ucll.project.domain.model.dish.Dish;
import ucll.project.domain.db.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DishController extends BaseController {
    private final CategoryRepositorySql categoryRepositorySql;
    private final DishRepositorySql dishRepositorySql;
    private final String validPattern = "[a-zA-Z_0-9 ]+";

    public DishController(UserRepository userRepository, DishRepositorySql dishRepositorySql, CategoryRepositorySql categoryRepositorySql) {
        super(userRepository);
        this.dishRepositorySql = dishRepositorySql;
        this.categoryRepositorySql = categoryRepositorySql;
    }

    public void  getAddDish(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categories", categoryRepositorySql.getAll());
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

    public void getRemoveDishes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setAttribute("dishes", dishRepositorySql.getAllDishes());
        request.getRequestDispatcher("/removeDishes.jsp").forward(request, response);
    }

    public void postRemoveDishes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String[] stringParams = request.getParameterValues("id[]");
	int[] intParams = new int[stringParams.length];

	for (int i = 0; i < stringParams.length; i++) {
	    intParams[i] = Integer.parseInt(stringParams[i]);
	}

        dishRepositorySql.removeDishes(intParams);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
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

    private void validateCategory (String category, ArrayList <String> errors){
        ArrayList<String> nameList = new ArrayList<>();
        List<Category> catlist = categoryRepositorySql.getAll();
        for (int i = 0; i < catlist.size(); i++) {
            nameList.add(catlist.get(i).getName());
        }
        if (category == null) {
            errors.add("Category is null");
        } else if (category.isEmpty()) {
            errors.add("Category is empty");
        } else if (!category.matches(validPattern)) {
            errors.add("Invalid characters");
        } else if (nameList.contains(category)) {
            errors.add("Category already exists");
        }
    }
}
