package ucll.project.domain.model.user;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private int userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private Role role;

    // hashed password
    private transient String hashedPassword;

    public User() {
    }

    public User(String userName, String firstName, String lastName, String email, Gender gender, Role role){
        setUserName(userName);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setGender(gender);
        setRole(role);
    }

    public void hashAndSetPassword(String password) {
        if (password.length() < 4) {
            throw new IllegalArgumentException("Too short password!");
        }
        String hashed = getPasswordToHashedPassword(password);
        setHashedPassword(hashed);
    }

    // This function will hash the password
    public String getPasswordToHashedPassword(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
        digest.update(password.getBytes(StandardCharsets.UTF_8));
        String hash = DatatypeConverter.printHexBinary(digest.digest());
        return hash;
    }

    public boolean isValidPassword(String password) {
        if (getHashedPassword() == null) {
            return false;
        }
        return getPasswordToHashedPassword(password).equals(getHashedPassword());
    }

    // Getters and setters and toString
    public int getUserId() {
        return this.userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public Gender getGender() {
        return this.gender;
    }

    public Role getRole() {
        return this.role;
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

    public void setUserId(int userId) {
        if(userId <= 0) throw new IllegalArgumentException("userid cannot be smaller than zero");
        this.userId = userId;
    }

    public void setUserName(String userName) {
        if(userName == null || userName.trim().isEmpty()) throw new IllegalArgumentException("Username cannot be empty");
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        if(firstName == null || firstName.trim().isEmpty()) throw new IllegalArgumentException("Firstname cannot be empty");
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if(lastName == null || lastName.trim().isEmpty()) throw new IllegalArgumentException("Lastname cannot be empty");
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        if(email == null || email.trim().isEmpty()) throw new IllegalArgumentException("Email cannot be empty");
        this.email = email;
    }

    public void setGender(Gender gender) {
        if(gender == null) throw new IllegalArgumentException("Gender cannot be empty");
        this.gender = gender;
    }

    public void setRole(Role role) {
        if(role == null) throw new IllegalArgumentException("Role cannot be empty");
        this.role = role;
    }

    public void setHashedPassword(String hashedPassword) {
        if(hashedPassword == null || hashedPassword.trim().isEmpty()) throw new IllegalArgumentException("Hashed password cannot be empty");
        this.hashedPassword = hashedPassword;
    }

    public String toString() {
        return "User(userId=" + this.getUserId() + ", userName=" + this.getUserName() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", email=" + this.getEmail() + ", gender=" + this.getGender() + ", role=" + this.getRole() + ", hashedPassword=" + this.getHashedPassword() + ")";
    }
}
