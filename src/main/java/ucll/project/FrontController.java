package ucll.project;

import ucll.project.controller.DishController;
import ucll.project.controller.MenuController;
import ucll.project.controller.UserController;
import ucll.project.domain.db.CategoryRepositorySql;
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
    private CategoryRepositorySql categoryRepositorySql;

    /* Controllers */
    private UserController userController;
    private DishController dishController;
    private MenuController menuController;
    private Properties properties;

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


        //DATABASES
        userRepository = new UserRepositoryDatabase(properties);
        dishRepositorySql = new DishRepositorySql(properties);
        categoryRepositorySql = new CategoryRepositorySql(properties);

        //Controllers
        userController = new UserController(userRepository);
        dishController = new DishController(userRepository, dishRepositorySql, categoryRepositorySql);
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
        request.setAttribute("dagmenu", menuController.getMenuOfTheDay());
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
//        System.out.println(String.format("%s %s\nResource = %s, Action = %s",
//                request.getMethod(), requestURI,
//                requestResource, requestAction
//        ));
//        System.out.println(requestResource);


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
            return;
        }

        if (method.equals("GET") && requestResource.equals("dish") && requestAction.equals("remove")) {
            dishController.getRemoveDishes(request, response);
            return;
        }

        if (method.equals("POST") && requestResource.equals("dish") && requestAction.equals("remove")) {
            dishController.postRemoveDishes(request, response);
            return;
        }

        if (method.equals("POST") && requestAction.equals("setCookie")) {
            setCookie(response, "language", request.getParameter("language"));
            if(requestResource.equals("weekMenu"))
                request.getRequestDispatcher("/weekMenu.jsp").forward(request, response);
            else
                request.getRequestDispatcher("/index.jsp").forward(request, response);

            return;
        }

        if(requestAction.equals("changeCookie")) {
            setCookie(response, "language", request.getParameter("language")); //Sets cookie

            if(requestResource.equals("weekMenu")) { //Send redirect for weekMenu
                String[] requestURL = String.valueOf(request.getRequestURL()).split("/w");
                response.sendRedirect(requestURL[0] + "/" + requestResource);
//                request.getRequestDispatcher("/weekMenu.jsp").forward(request, response);
                return;
            }
            else { //Send redirect for index
                String[] requestURL = String.valueOf(request.getRequestURL()).split("/i");
                response.sendRedirect(requestURL[0] + "/" + requestResource);
//                request.getRequestDispatcher("/index.jsp").forward(request, response);
                return;
            }
        }

        if (method.equals("GET") && requestResource.equals("index")) {
            if(request.getParameter("language") == null || request.getParameter("language").trim().isEmpty())
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            else {
                setCookie(response, "language", request.getParameter("language")); //Sets cookie

                String[] requestURL = String.valueOf(request.getRequestURL()).split("/i");
                response.sendRedirect(requestURL[0] + "/" + requestResource);
            }
            return;
        }

        if(method.equals("GET") && requestResource.equals("weekMenu")){
            if(request.getParameter("language") == null || request.getParameter("language").trim().isEmpty())
                  request.getRequestDispatcher("/weekMenu.jsp").forward(request, response);
            else {
                setCookie(response, "language", request.getParameter("language")); //Sets cookie

                String[] requestURL = String.valueOf(request.getRequestURL()).split("/w");
                response.sendRedirect(requestURL[0] + "/" + requestResource);
            }
            return;
        }

        if (method.equals("GET") && requestResource.equals("menu") && requestAction.equals("make")) {
            menuController.getMakeMenu(request, response);
        }

        if (method.equals("POST") && requestResource.equals("menu") && requestAction.equals("updateCampus")) {
            menuController.getMakeMenu(request, response);
        }

        // if no route was found, show error. Make sure to return after each forward!
        request.getRequestDispatcher("/error.jsp").forward(request, response);

    }

    private void setCookie(HttpServletResponse response, String cookieName, String cookieValue) {
        response.addCookie(new Cookie(cookieName, cookieValue));
    }
}
