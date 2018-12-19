package ucll.project;

import ucll.project.controller.DishController;
import ucll.project.controller.MenuController;
import ucll.project.controller.UserController;
import ucll.project.domain.db.DishRepositorySql;
import ucll.project.domain.db.UserRepository;
import ucll.project.domain.db.UserRepositoryDatabase;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * This is the FrontController
 * All requests will go trough here, and this class will
 * decide which controller to call.
 */
@WebServlet(urlPatterns = "/", loadOnStartup = 1)
public class FrontController extends HttpServlet {

    /* Repositories */
    private UserRepository userRepository;
    private DishRepositorySql dishRepositorySql;

    /* Controllers */
    private UserController userController;
    private DishController dishController;
    private Properties properties;
    private MenuController menuController;

    public void init() throws ServletException {
        super.init();

        properties = new Properties();
        ServletContext context = getServletContext();
        properties.setProperty("user", context.getInitParameter("user"));
        properties.setProperty("password", context.getInitParameter("password"));
        properties.setProperty("ssl", context.getInitParameter("ssl"));
        properties.setProperty("sslfactory", context.getInitParameter("sslfactory"));
        properties.setProperty("sslmode", context.getInitParameter("sslmode"));
        properties.setProperty("url", context.getInitParameter("url"));

        userRepository = new UserRepositoryDatabase(properties);
        dishRepositorySql = new DishRepositorySql(properties);

        userController = new UserController(userRepository);
        dishController = new DishController(userRepository, dishRepositorySql);

        menuController = new MenuController(userRepository, properties);
    }

    public FrontController() {
        super();
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

        if (request.getSession().getAttribute("userid") != null) {
            int userId = (int) request.getSession().getAttribute("userid");
            request.setAttribute("user", userRepository.get(userId));
        }


        // logic to handle what controller
        System.out.println(String.format("%s %s\nResource = %s, Action = %s",
                request.getMethod(), requestURI,
                requestResource, requestAction
        ));
        System.out.println(requestResource);


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

        if (method.equals("POST") && requestResource.equals("index") && requestAction.equals("cookies")) {
            String lan = request.getParameter("language");
            Cookie cookie = new Cookie("language", lan);
            response.addCookie(cookie);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        if(method.equals("GET") && requestResource.equals("index") && requestAction.equals("cookies")) {
            String lang = request.getParameter("language");
            String other;
            Cookie cookie = new Cookie("language", lang);
            response.addCookie(cookie);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        if(requestResource.equals("index") && requestAction.equals("weekMenu")) {

            request.getRequestDispatcher("/weekMenu.jsp").forward(request, response);
        }

        if (requestResource.equals("index")) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        // if no route was found, show error. Make sure to return after each forward!
        request.getRequestDispatcher("/error.jsp").forward(request, response);

    }
}
