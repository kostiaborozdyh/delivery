package com.example.demos.model.dao;

import com.example.demos.DB.DBHelper;
import com.example.demos.model.utils.CreateMessage;
import com.example.demos.model.utils.SendEmail;
import com.example.demos.model.entity.User;
import com.example.demos.model.entity.ValidList;
import com.example.demos.model.utils.Validation;
import com.example.demos.security.Security;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public static final String SQL_GET_USER_VALID = "SELECT * FROM user u WHERE u.login=? AND u.password=?";
    public static final String SQL_GET_USER_VALID_FROM_EMAIL = "SELECT * FROM user u WHERE u.email=? AND u.password=?";
    public static final String SQL_GET_USER = "SELECT * FROM user u WHERE u.login=?";
    public static final String SQL_GET_USER_FROM_ID = "SELECT * FROM user u WHERE u.id=?";
    public static final String SQL_GET_USERS = "SELECT * FROM user u WHERE u.role_id!=3";
    public static final String SQL_GET_EMAIL = "SELECT * FROM user u WHERE u.email=?";
    public static final String SQL_INSERT_USER = "INSERT INTO user(login,password,first_name,last_name,phone_number,email,role_id,notify,ban)  VALUES (?,?,?,?,?,?,?,?,?)";
    public static final String SQL_CHANGE_MONEY = "UPDATE delivery.user d SET d.money = ? WHERE d.id=?";
    public static final String SQL_BLOCK_USER = "UPDATE delivery.user d SET d.ban = 'yes' WHERE d.id=?";
    public static final String SQL_UN_BLOCK_USER = "UPDATE delivery.user d SET d.ban = 'no' WHERE d.id=?";
    public static final String SQL_DELETE_USER = "DELETE FROM delivery.user d WHERE d.id =?";
    public static final String SQL_GET_USER_EMAIL = "SELECT * FROM user u WHERE u.login=?";
    public static final String SQL_GET_USER_EMAIL_BY_ID = "SELECT * FROM user u WHERE u.id=?";
    public static final String SQL_CHANGE_PASSWORD = "UPDATE delivery.user d SET d.password = ? WHERE d.email=?";
    public static final String SQL_EDIT_USER = "UPDATE delivery.user d \n" +
            "SET d.first_name = ?, d.last_name = ?, \n" +
            "d.phone_number = ?, d.email = ?, d.notify = ?\n" +
            "WHERE d.id=?";
    public static final String SQL_EDIT_USER_AND_PASSWORD = "UPDATE delivery.user d \n" +
            "SET d.password = ?, d.first_name = ?, d.last_name = ?, \n" +
            "d.phone_number = ?, d.email = ?,  d.notify = ?\n" +
            "WHERE d.id=?";

    public static User userValid(String login, String password) {
        User user = null;
        PreparedStatement pst;
        try (Connection con = DBHelper.getInstance().getConnection()) {
            if (Validation.emailNameValid(login)) {
                pst = con.prepareStatement(SQL_GET_USER_VALID_FROM_EMAIL);
            } else {
                pst = con.prepareStatement(SQL_GET_USER_VALID);
            }
            pst.setString(1, login);
            pst.setString(2, Security.hashPassword(password));
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setLogin(rs.getString("login"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setPhoneNumber(rs.getString("phone_number"));
                    user.setEmail(rs.getString("email"));
                    user.setRole_id(rs.getInt("role_id"));
                    user.setMoney(rs.getInt("money"));
                    user.setNotify(rs.getString("notify"));
                    user.setBan(rs.getString("ban"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static boolean getUserNotify(Integer id) {
        String notify = null;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_USER_FROM_ID)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    notify = rs.getString("notify");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (notify.equals("yes"));
    }

    public static boolean loginIsValid(String login) {
        String userLogin = null;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_USER)) {
            pst.setString(1, login);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    userLogin = rs.getString("login");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (userLogin == null);
    }

    public static boolean emailIsValid(String email) {
        String userEmail = null;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_EMAIL)) {
            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    userEmail = rs.getString("email");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (userEmail == null);
    }


    public static boolean insertUser(User user) {
        int count = 0;
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_INSERT_USER);
            connection.setAutoCommit(false);
            pst.setString(1, user.getLogin());
            pst.setString(2, Security.hashPassword(user.getPassword()));
            pst.setString(3, user.getFirstName());
            pst.setString(4, user.getLastName());
            pst.setString(5, user.getPhoneNumber());
            pst.setString(6, user.getEmail());
            pst.setInt(7, user.getRole_id());
            pst.setString(8, user.getNotify());
            pst.setString(9, "no");
            count = pst.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(pst);
        }
        return count > 0;
    }


    public static void changeMoney(Integer orderId, Integer value, Integer money) {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_CHANGE_MONEY);
            connection.setAutoCommit(false);
            pst.setInt(1, money - value);
            pst.setInt(2, OrderDao.getUserId(orderId));
            pst.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            ex.printStackTrace();
        } finally {
            close(connection);
            close(pst);
        }
    }

    public static boolean changePassword(String email, String password) {
        int count = 0;
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_CHANGE_PASSWORD);
            connection.setAutoCommit(false);
            pst.setString(1, Security.hashPassword(password));
            pst.setString(2, email);
            count = pst.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(pst);
        }
        return count > 0;
    }

    public static boolean refillMoney(Integer userId, Integer value, Integer money) {
        int count = 0;
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_CHANGE_MONEY);
            connection.setAutoCommit(false);
            pst.setInt(1, money + value);
            pst.setInt(2, userId);
            count = pst.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            ex.printStackTrace();
        } finally {
            close(connection);
            close(pst);
        }
        return count > 0;
    }

    public static User editUser(User user) {
        int count = 0, k = 1;
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            connection.setAutoCommit(false);
            if (user.getPassword().equals("Password1")) {
                pst = connection.prepareStatement(SQL_EDIT_USER);
            } else {
                pst = connection.prepareStatement(SQL_EDIT_USER_AND_PASSWORD);
                pst.setString(1, Security.hashPassword(user.getPassword()));
                k++;
            }
            pst.setString(k, user.getFirstName());
            pst.setString(++k, user.getLastName());
            pst.setString(++k, user.getPhoneNumber());
            pst.setString(++k, user.getEmail());
            pst.setString(++k, user.getNotify());
            pst.setInt(++k, user.getId());
            count = pst.executeUpdate();
            user.setPassword("");
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(pst);
        }
        if (count > 0) return user;
        else return null;
    }

    public static String getUserEmail(String login) {
        String email = null;
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_USER_EMAIL)) {
            pst.setString(1, login);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    email = rs.getString("email");
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return email;
    }

    public static String getUserEmail(Integer id) {
        String email = null;
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_USER_EMAIL_BY_ID)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    email = rs.getString("email");
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return email;
    }

    public static List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_USERS)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setLogin(rs.getString("login"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setEmail(rs.getString("email"));
                    user.setBan(rs.getString("ban"));
                    userList.add(user);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public static void blockUser(Integer id) throws MessagingException, UnsupportedEncodingException {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_BLOCK_USER);
            connection.setAutoCommit(false);
            pst.setInt(1, id);
            pst.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            ex.printStackTrace();
        } finally {
            close(connection);
            close(pst);
        }
        SendEmail.send(getUserEmail(id), CreateMessage.blockUser());
    }

    public static void unBlockUser(Integer id) throws MessagingException, UnsupportedEncodingException {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_UN_BLOCK_USER);
            connection.setAutoCommit(false);
            pst.setInt(1, id);
            pst.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            ex.printStackTrace();
        } finally {
            close(connection);
            close(pst);
        }
        SendEmail.send(getUserEmail(id), CreateMessage.unBlockUser());
    }

    public static void deleteUser(Integer id) throws MessagingException, UnsupportedEncodingException {
        String email = getUserEmail(id);
        OrderDao.deleteOrder(id);
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_DELETE_USER);
            connection.setAutoCommit(false);
            pst.setInt(1, id);
            pst.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            ex.printStackTrace();
        } finally {
            close(connection);
            close(pst);
        }
        SendEmail.send(email, CreateMessage.deleteUser());
    }
    private static void close(PreparedStatement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
