package ucll.project.domain.db;

import ucll.project.domain.model.user.Gender;
import ucll.project.domain.model.user.InvalidLogin;
import ucll.project.domain.model.user.Role;
import ucll.project.domain.model.user.User;

import java.sql.*;
import java.util.ArrayList;
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
    public void createUser(User user, String password) {
        try {
            this.connection = DriverManager.getConnection(properties.getProperty("url"),this.properties);

            user.hashAndSetPassword(password);

            PreparedStatement statement = connection.prepareStatement("insert into \"menu-teamtuur12\".\"user\" (username, firstname, lastname, email, gender, password, salt, roleid) values (?, ? , ?, ?, ? ,?, ?, ?)");


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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private int getRoleId(Role role) {
        int roleid = 0;
        try {
            this.connection = DriverManager.getConnection(properties.getProperty("url"),this.properties);
            PreparedStatement statement = connection.prepareStatement("Select roleid from \"menu-teamtuur12\".role where name = ?");
            statement.setString(1, role.getRole());
            ResultSet set  = statement.executeQuery();


            while (set.next()) {
                roleid = set.getInt(1);
            }
            statement.close();
            connection.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return roleid;
    }

    @Override
    public User get(int userId) {
        User u = new User();

        try {
            this.connection = DriverManager.getConnection(properties.getProperty("url"),this.properties);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"menu-teamtuur12\".\"user\" WHERE userid = ?");
            statement.setInt(1, userId);


            ResultSet set = statement.executeQuery();
            while (set.next()) {
                u = makeUser(set);
            }

            statement.close();
            connection.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return u;
    }

    private User makeUser(ResultSet set) {
        User u = new User();
        try {
            if (set.next()) {
                u.setUserId(set.getInt("userid"));
                u.setUserName(set.getString("username"));
                u.setFirstName(set.getString("firstname"));
                u.setLastName(set.getString("lastname"));
                u.setEmail(set.getString("email"));

                if(set.getString("gender").equals("FEMALE")) {
                    u.setGender(Gender.FEMALE);
                } else {
                    u.setGender(Gender.MALE);
                }
                u.setHashedPassword(set.getString("password"));

                if(set.getInt("roleid") == 1) {
                    u.setRole(Role.ADMIN);
                } else {
                    u.setRole(Role.CAMPUSADMIN);
                }
            }
        } catch (SQLException e) {
            e.getErrorCode();
        }
        return u;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try {
            this.connection = DriverManager.getConnection(properties.getProperty("url"),this.properties);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"menu-teamtuur12\".\"user\"");

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                User u = makeUser(set);
                users.add(u);
            }

            statement.close();
            connection.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }



    @Override
    public User loginUser(String username, String password) throws InvalidLogin {
        User ret = new User();

        try {
            this.connection = DriverManager.getConnection(properties.getProperty("url"),this.properties);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"menu-teamtuur12\".\"user\" WHERE username = ?");
            statement.setString(1, username);

            ResultSet set = statement.executeQuery();

            ret = makeUser(set);

            statement.close();
            connection.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(ret.getPasswordToHashedPassword(password));
        if(ret == null || !ret.isValidPassword(password)) {
            throw new InvalidLogin("Invalid password!");
        }

        return ret;
    }

    @Override
    public void update(User user) {
        try {
            this.connection = DriverManager.getConnection(properties.getProperty("url"),this.properties);
            PreparedStatement statement = connection.prepareStatement("UPDATE \"menu-teamtuur12\".\"user\" set userid=? username=? firstname=? lastname=? email=? gender=? password=? roleid=? ");
            statement.setInt(1, user.getUserId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getEmail());
            statement.setString(6, String.valueOf(user.getGender()));
            statement.setString(7, user.getHashedPassword());
            statement.setInt(8, getRoleId(user.getRole()));
            statement.executeUpdate();

            statement.close();
            connection.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(User user) {
        try {
            this.connection = DriverManager.getConnection(properties.getProperty("url"),this.properties);
            PreparedStatement statement = connection.prepareStatement("DELETE * FROM \"menu-teamtuur12\".\"user\" WHERE userid = ?");
            statement.setInt(1, user.getUserId());
            statement.execute();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
