package ucll.project.ui;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ucll.project.domain.model.user.Gender;
import ucll.project.domain.model.user.Role;
import ucll.project.domain.model.user.User;
import ucll.project.ui.pages.HomePage;
import ucll.project.ui.pages.LoginPage;
import ucll.project.ui.pages.SignUpPage;

import static org.junit.Assert.assertEquals;

public class UserTest extends BaseUITest {
    private static User user;
    private static String password;

    @Before
    public void generateUser() {
        user = new User();

        String userName = RandomStringUtils.random(5, true, false);
        password = RandomStringUtils.random(5, true, false).toLowerCase();

        user.setEmail(userName + "@email.com");
        user.setUserName(userName);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setGender(Gender.MALE);
        user.setRole(Role.ADMIN);

        user = new User(userName, "firstName", "lastName", userName + "@email.com", Gender.MALE, Role.ADMIN);
        user.hashAndSetPassword(password);

        System.out.println(user.getUserName());
        System.out.println(password);
    }

    @After
    public void deleteUser() {

    }
}
