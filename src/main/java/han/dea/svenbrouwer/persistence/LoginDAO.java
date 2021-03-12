package han.dea.svenbrouwer.persistence;

import han.dea.svenbrouwer.rest.LoginDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDAO {

    private Logger logger = Logger.getLogger(getClass().getName());

    private Properties databaseProperties;

    public LoginDAO(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties.getProperties();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<LoginDTO> findAllUsers() {
        List<LoginDTO> users = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.getProperty("connectionstring"),
                    databaseProperties.getProperty("user"), databaseProperties.getProperty("password"));
            PreparedStatement statement = connection.prepareStatement("SELECT * from users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                LoginDTO loginDTO = new LoginDTO(resultSet.getString("Token"),
                        resultSet.getString("User"), resultSet.getString("Password")
                );
                users.add(loginDTO);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean addUser(LoginDTO user) {
        try (Connection connection = DriverManager.getConnection(databaseProperties.getProperty("connectionstring"),
                databaseProperties.getProperty("user"), databaseProperties.getProperty("password"))) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (Token, User, Password) VALUES (?, ?, ?)");
            statement.setString(1, user.getToken());
            statement.setString(2, user.getUser());
            statement.setString(3, user.getPassword());
            return statement.execute();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't insert item " + user, e);
        }
        return false;
    }

    public LoginDTO getUserToken(String user, String password) {
        LoginDTO userToken = new LoginDTO();
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.getProperty("connectionstring"),
                    databaseProperties.getProperty("user"), databaseProperties.getProperty("password"));
            PreparedStatement statement = connection.prepareStatement("SELECT Token, User from users where User =? and Password =?");

            statement.setString(1, user);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userToken.setToken(resultSet.getString("Token"));
                userToken.setUser(resultSet.getString("User"));

            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userToken;
    }

    //    public boolean deleteItem(int id) {
//        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString())) {
//            PreparedStatement statement = connection.prepareStatement("DELETE FROM items WHERE id = ?");
//            statement.setInt(1, id);
//            return statement.execute();
//        } catch (SQLException e) {
//            logger.log(Level.SEVERE, "Can't delete item with id " + id, e);
//        }
//        return false;
//    }
}
