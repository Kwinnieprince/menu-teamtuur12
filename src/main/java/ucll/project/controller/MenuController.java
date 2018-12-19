package ucll.project.controller;

import ucll.project.domain.db.UserRepository;

import java.util.Properties;

public class MenuController extends BaseController {


    public MenuController(UserRepository userRepository, Properties properties) {
        super(userRepository);
    }

}
