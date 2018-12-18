package ucll.project.domain.model.user;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is a UnitTest that tests the User class
 */
public class UserTest {

    @Test
    public void CreateUserTest(){
        User user = new User(
                "userName",
                "firstName",
                "lastName",
                "email@example.com",
                Gender.FEMALE,
                Role.ADMIN
        );
        assertEquals(user.getUserName(), "userName");
    }
}
