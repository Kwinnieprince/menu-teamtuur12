package ucll.project;

import ucll.project.controller.DishController;
import ucll.project.controller.UserController;
import ucll.project.domain.model.dish.Dish;
import ucll.project.domain.model.user.UserRepository;
import ucll.project.domain.model.user.UserRepositoryMemory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This is the FrontController
 * All requests will go trough here, and this class will
 * decide which controller to call.
 */
@WebServlet(urlPatterns = "/", loadOnStartup = 1)
public class FrontController extends HttpServlet {
    private UserRepository userRepository;

    public FrontController() {
        super();
        // You could switch here based on config
        userRepository = new UserRepositoryMemory();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handle(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handle(request, response);
    }

    private void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the request URI and parse it
        String requestURI = request.getRequestURI();

        String method = request.getMethod();
        String requestResource;
        String requestAction = "";
        if (requestURI.equals("/"))
            requestResource = "index";
        else {
            String[] requestURIparts = requestURI.split("/");
            requestResource = requestURIparts[1];
            requestAction = requestURIparts.length > 2 ? requestURIparts[2] : "";
        }
        /** Eg.: GET /user/login
         *  method=GET
         *  requestResource=user
         *  requestAction=login
         */

        // controllers
        UserController userController = new UserController(userRepository);
        DishController dishController = new DishController(userRepository);
        MenuController menuController = new MenuController(userRepository);

        if (request.getSession().getAttribute("userid") != null) {
            int userId = (int) request.getSession().getAttribute("userid");
            request.setAttribute("user", userRepository.get(userId));
        }


        // logic to handle what controller
        System.out.println(String.format("%s %s\nResource = %s, Action = %s",
                request.getMethod(), requestURI,
                requestResource, requestAction
        ));


        if (method.equals("GET") && requestResource.equals("user") && requestAction.equals("login")) {
            userController.getLogin(request, response);
            return; // stop execution
        }
        if (method.equals("POST") && requestResource.equals("user") && requestAction.equals("login")) {
            userController.postLogin(request, response);
            return;
        }
        if (/* any method */         requestResource.equals("user") && requestAction.equals("signup")) {
            userController.handleSignup(request, response);
            return;
        }

        if (/* any method */         requestResource.equals("user") && requestAction.equals("logout")) {
            userController.handleLogout(request, response);
            return;
        }

        if (method.equals("GET") && requestResource.equals("dish") && requestAction.equals("add")) {
            dishController.getAddDish(request, response);
            return;
        }

        if (method.equals("POST") && requestResource.equals("dish") && requestAction.equals("add")) {
            dishController.postAddDish(request, response);
        }


        if (requestResource.equals("index")) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        // if no route was found, show error. Make sure to return after each forward!
        request.getRequestDispatcher("/error.jsp").forward(request, response);

    }
}
