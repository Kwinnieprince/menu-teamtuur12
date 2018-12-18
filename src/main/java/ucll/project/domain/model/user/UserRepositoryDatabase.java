package ucll.project.domain.model.user;

import com.sun.xml.internal.bind.v2.model.core.PropertyInfo;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class UserRepositoryDatabase implements UserRepository {

    private Properties properties;
    private Connection connection;

    public UserRepositoryDatabase(Properties properties) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.properties = properties;
    }

    @Override
    public void createUser(User user, String password) throws SQLException {
        try {
            this.connection = DriverManager.getConnection(properties.getProperty("url"),this.properties);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        user.hashAndSetPassword(password);

        PreparedStatement statement = connection.prepareStatement("insert into \"user\" (username, firstname, lastname, email, gender, password, salt, roleid) values (?, ? , ?, ?, ? ,?, ?, ?)");


        statement.setString(1, user.getUserName());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getLastName());
        statement.setString(4, user.getEmail());
        statement.setString(5, String.valueOf(user.getGender()));
        statement.setString(6, user.getHashedPassword());
        statement.setInt(7,0);
        statement.setInt(8, getRoleId(user.getRole()));

        statement.execute();

        statement.close();
        connection.close();

    }

    private int getRoleId(Role role) throws SQLException {
        try {
            this.connection = DriverManager.getConnection(properties.getProperty("url"),this.properties);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        PreparedStatement statement = connection.prepareStatement("Select roleid from role where name = ?");
        statement.setString(1, role.getRole());
        ResultSet set  = statement.executeQuery();

        int roleid = 0;
        while (set.next()) {
            roleid = set.getInt(1);
        }
        statement.close();
        connection.close();
        return roleid;
    }

    @Override
    public User get(int userId) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User loginUser(String username, String password) throws InvalidLogin {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
