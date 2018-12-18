package ucll.project.ui;

import org.junit.Test;
import org.openqa.selenium.By;
import ucll.project.ui.pages.HomePage;
import ucll.project.ui.pages.LoginPage;

import static org.junit.Assert.assertEquals;

public class LoginPageTest extends BaseUITest {
    

    @Test
    public void loginTestWithInvalidUsernameExpectFailure() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage = loginPage.loginAsExpectingError("invalid", "user");
        assertEquals("Please sign in", loginPage.getPageTitle());
        assertEquals("Invalid username", loginPage.getErrorMessage());

    }

    @Test
    public void loginTestWithoutUsernameExpectFailure() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage = loginPage.loginAsExpectingError("", "user");
        assertEquals("Please sign in", loginPage.getPageTitle());

    }

    @Test
    public void loginTestWithoutPasswordExpectFailure() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage = loginPage.loginAsExpectingError("user", "");
        assertEquals("Please sign in", loginPage.getPageTitle());

    }

}
