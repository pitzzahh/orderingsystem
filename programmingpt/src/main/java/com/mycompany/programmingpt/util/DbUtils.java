package com.mycompany.programmingpt.util;

import com.mycompany.programmingpt.model.MenuItem;
import com.mycompany.programmingpt.model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DbUtils {

    private static Connection getConnection() throws SQLException {
        Properties config = ResourceUtils.getConfig();
        String url = (String) config.get("database.url");
        String user = (String) config.get("database.user");
        String pass = (String) config.get("database.pass");

        return DriverManager.getConnection(url, user, pass);
    }

    public static List<MenuItem> getMenuItems() {
        String query = "SELECT * FROM menu_items";
        List<MenuItem> menuItemList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MenuItem menuItem = new MenuItem();
                menuItem.setId(resultSet.getInt("id"));
                menuItem.setName(resultSet.getString("name"));
                menuItem.setPrice(resultSet.getDouble("price"));
                menuItemList.add(menuItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return menuItemList;
    }

    public static User getUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username=? AND password=? LIMIT 1";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, username);
            preparedStatement.setObject(2, encryptPassword(password));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setUsername(resultSet.getString("username"));

                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String encryptPassword(String pass) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pass.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }


}
