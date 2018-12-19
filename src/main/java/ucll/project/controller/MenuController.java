package ucll.project.controller;

import ucll.project.domain.model.menu.Menu;
import ucll.project.domain.model.user.UserRepository;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class MenuController extends BaseController {


    public MenuController(UserRepository userRepository, Properties properties) {
        super(userRepository);
    }

}
