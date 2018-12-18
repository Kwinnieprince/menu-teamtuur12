package ucll.project.domain.model.user;

public class InvalidLogin extends Exception {
    public InvalidLogin(String error) {
        super(error);
    }
}
