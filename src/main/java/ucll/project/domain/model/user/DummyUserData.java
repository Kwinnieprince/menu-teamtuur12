package ucll.project.domain.model.user;

import ucll.project.domain.db.UserRepository;

public class DummyUserData {
    public static void addData(UserRepository userRepository) {
        userRepository.createUser(
                new User("admin",
                        "admin", "user",
                        "admin@example.com",
                        Gender.FEMALE, Role.ADMIN
                ),
                "admin" // password
        );

        userRepository.createUser(
                new User("bob",
                        "bob", "user",
                        "bob@example.com",
                        Gender.MALE, Role.CAMPUSADMIN
                ),
                "bob123" // password
        );

        userRepository.createUser(
                new User("support",
                        "support", "user",
                        "support@example.com",
                        Gender.MALE, Role.CAMPUSADMIN
                ),
                "support" // password
        );
    }
}
