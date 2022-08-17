package com.gmail.KostiaBorozdyh.model.dao;

import com.gmail.KostiaBorozdyh.DB.DBHelper;
import com.gmail.KostiaBorozdyh.model.entity.User;
import com.gmail.KostiaBorozdyh.model.utils.Validation;
import com.gmail.KostiaBorozdyh.security.Security;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for User entity
 */
public class UserDao {
    private static final Logger log = Logger.getLogger(UserDao.class);
    public static final String SQL_GET_USER_VALID = "SELECT * FROM user u WHERE u.login=? AND u.password=?";
    public static final String SQL_GET_USER_VALID_FROM_EMAIL = "SELECT * FROM user u WHERE u.email=? AND u.password=?";
    public static final String SQL_GET_USER = "SELECT * FROM user u WHERE u.login=?";
    public static final String SQL_GET_USER_FROM_ID = "SELECT * FROM user u WHERE u.id=?";
    public static final String SQL_GET_USERS = "SELECT * FROM user u WHERE u.role_id!=3 limit ?,5";
    public static final String SQL_GET_EMAIL = "SELECT * FROM user u WHERE u.email=?";
    public static final String SQL_INSERT_USER = "INSERT INTO user(login,password,first_name,last_name,phone_number,email,role_id,notify,ban)  VALUES (?,?,?,?,?,?,?,?,?)";
    public static final String SQL_CHANGE_MONEY = "UPDATE delivery.user d SET d.money = ? WHERE d.login=?";
    public static final String SQL_BLOCK_USER = "UPDATE delivery.user d SET d.ban = 'yes' WHERE d.id=?";
    public static final String SQL_UN_BLOCK_USER = "UPDATE delivery.user d SET d.ban = 'no' WHERE d.id=?";
    public static final String SQL_DELETE_USER = "DELETE FROM delivery.user d WHERE d.id =?";
    public static final String SQL_GET_USER_EMAIL = "SELECT * FROM user u WHERE u.login=?";
    public static final String SQL_GET_USER_EMAIL_BY_ID = "SELECT * FROM user u WHERE u.id=?";
    public static final String SQL_CHANGE_PASSWORD = "UPDATE delivery.user d SET d.password = ? WHERE d.email=?";
    public static final String SQL_GET_USER_COUNT = "SELECT count(1) FROM user u WHERE u.role_id!=3";
    public static final String SQL_GET_USER_ID = "SELECT * FROM delivery.order d WHERE d.id = ?";
    public static final String SQL_EDIT_USER = "UPDATE delivery.user d \n" +
            "SET d.first_name = ?, d.last_name = ?, \n" +
            "d.phone_number = ?, d.email = ?, d.notify = ?\n" +
            "WHERE d.id=?";
    public static final String SQL_EDIT_USER_AND_PASSWORD = "UPDATE delivery.user d \n" +
            "SET d.password = ?, d.first_name = ?, d.last_name = ?, \n" +
            "d.phone_number = ?, d.email = ?,  d.notify = ?\n" +
            "WHERE d.id=?";

    /**
     * Return User by User Login and User Password
     *
     * @param login    User Login
     * @param password User Password
     * @return User entity or null if wasn't found
     */
    public static User userValid(String login, String password) {
        User user = null;
        PreparedStatement pst = null;
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
                    user.setRoleId(rs.getInt("role_id"));
                    user.setMoney(rs.getInt("money"));
                    user.setNotify(rs.getString("notify"));
                    user.setBan(rs.getString("ban"));
                }
            }
            log.info("Get user from data base by login - " + login);
        } catch (Exception ex) {
            log.error("Problem with getting user from data base by login - " + login);
            log.error("Exception - " + ex);
        } finally {
            close(pst);
        }
        return user;
    }

    /**
     * Return User has Notify by User identifier
     *
     * @param id User identifier
     * @return true if User has notified, false otherwise
     */
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
            log.info("Check that user with id - " + id + ", has notify");
        } catch (SQLException ex) {
            log.error("Problem with checking that user with id - " + id + ", has notify");
            log.error("Exception - " + ex);
        }
        return (notify.equals("yes"));
    }

    /**
     * Return User identifier by Order identifier
     *
     * @param orderId Order identifier
     * @return User identifier or 0 if wasn't found
     */
    public static Integer getUserIdByOrderId(Integer orderId) {
        int id = 0;
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_USER_ID)) {
            pst.setInt(1, orderId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("user_id");
                }
            }
            log.info("Get userId from data base by orderId - " + orderId);
        } catch (SQLException ex) {
            log.error("Problem with getting userId from data base by orderId - " + orderId);
            log.error("Exception - " + ex);
        }
        return id;
    }

    /**
     * Return whether the User Login is unique
     *
     * @param login User Login
     * @return true if User has unique Login, false otherwise
     */
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
            log.info("Check that user with login - " + login + ", consist in data base");
        } catch (SQLException ex) {
            log.error("Problem with checking that user with login - " + login + ", consist in data base");
            log.error("Exception - " + ex);
        }
        return (userLogin == null);
    }

    /**
     * Return whether the User Email is unique
     *
     * @param email User Email
     * @return true if User has unique Email, false otherwise
     */
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
            log.info("Check that user with email - " + email + ", consist in data base");
        } catch (SQLException ex) {
            log.error("Problem with checking that user with email - " + email + ", consist in data base");
            log.error("Exception - " + ex);
        }
        return (userEmail == null);
    }

    /**
     * Insert new User
     *
     * @param user User
     * @return true if User has added to dateBase, false otherwise
     */
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
            pst.setInt(7, user.getRoleId());
            pst.setString(8, user.getNotify());
            pst.setString(9, "no");
            count = pst.executeUpdate();
            connection.commit();
            log.info("Add user with login - " + user.getLogin() + ", into data base");
        } catch (Exception ex) {
            rollback(connection);
            log.error("Problem with adding user with login - " + user.getLogin() + ", into data base");
            log.error("Exception - " + ex);
        } finally {
            close(connection);
            close(pst);
        }
        return count > 0;
    }

    /**
     * Change User money by User Login
     *
     * @param login User Login
     * @param money User Money
     */
    public static void changeMoney(String login, Integer money) {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_CHANGE_MONEY);
            connection.setAutoCommit(false);
            pst.setInt(1, money);
            pst.setString(2, login);
            pst.executeUpdate();
            connection.commit();
            log.info("Change money for user with login - " + login + ", in data base, on - " + money + "$");
        } catch (SQLException ex) {
            rollback(connection);
            log.error("Problem with changing money for user with login - " + login + ", in data base, on - " + money + "$");
            log.error("Exception - " + ex);
        } finally {
            close(connection);
            close(pst);
        }
    }

    /**
     * Change User password by User Email
     *
     * @param email    User Email
     * @param password User password
     * @return true if User has changed password in dateBase, false otherwise
     */
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
            log.info("Change password for user with email - " + email + ", in data base");
        } catch (Exception ex) {
            rollback(connection);
            log.error("Problem with changing password for user with email - " + email + ", in data base");
            log.error("Exception - " + ex);
        } finally {
            close(connection);
            close(pst);
        }
        return count > 0;
    }

    /**
     * Update User
     *
     * @param user User
     * @return user if User updated in dateBase, null otherwise
     */
    public static User editUser(User user) {
        int count = 0, k = 1;
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            connection.setAutoCommit(false);
            if (user.getPassword().equals("")) {
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
            log.info("Edit user with email - " + user.getEmail() + ", in data base");
        } catch (Exception ex) {
            rollback(connection);
            log.error("Problem with editing user with email - " + user.getEmail() + ", in data base");
            log.error("Exception - " + ex);
        } finally {
            close(connection);
            close(pst);
        }
        if (count > 0) {
            return user;
        }
        return null;
    }

    /**
     * Return User Email by User Login
     *
     * @param login User Login
     * @return User Email if User was in dateBase, null otherwise
     */
    public static String getUserEmailByUserLogin(String login) {
        String email = null;
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_USER_EMAIL)) {
            pst.setString(1, login);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    email = rs.getString("email");
                }

            }
            log.info("Get user email from data base by user login - " + login);
        } catch (SQLException ex) {
            log.error("Problem with getting user email from data base by user login - " + login);
            log.error("Exception - " + ex);
        }
        return email;
    }

    /**
     * Return User Email by User identifier
     *
     * @param id User identifier
     * @return User Email if User was in dateBase, null otherwise
     */
    public static String getUserEmailByUserId(Integer id) {
        String email = null;
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_USER_EMAIL_BY_ID)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    email = rs.getString("email");
                }
            }
            log.info("Get user email from data base by user id - " + id);
        } catch (SQLException ex) {
            log.error("Problem with getting user email from data base by user id - " + id);
            log.error("Exception - " + ex);
        }
        return email;
    }

    /**
     * Return userList by offset with limit 5
     *
     * @param skip int offset
     * @return List of User items. If any problems returns empty list.
     */
    public static List<User> getUsers(int skip) {
        List<User> userList = new ArrayList<>();
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_USERS)) {
            pst.setInt(1, skip);
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
            log.info("Get all users from data base with offset - " + skip);
        } catch (SQLException ex) {
            log.error("Problem with getting all users from data base with offset - " + skip);
            log.error("Exception - " + ex);
        }
        return userList;
    }

    /**
     * Return number of all users
     *
     * @return number of all users or 0 if wasn't count
     */
    public static Integer getUserCount() {
        int count = 0;
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_USER_COUNT)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    count = rs.getInt("count(1)");
                }
            }
            log.info("Get user count from data base");
        } catch (SQLException ex) {
            log.error("Problem with getting user count from data base");
            log.error("Exception - " + ex);
        }
        return count;
    }

    /**
     * Blocking User by User identifier
     *
     * @param id User identifier
     */
    public static void blockUser(Integer id) {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_BLOCK_USER);
            connection.setAutoCommit(false);
            pst.setInt(1, id);
            pst.executeUpdate();
            connection.commit();
            log.info("Block user in data base by user id - " + id);
        } catch (SQLException ex) {
            rollback(connection);
            log.error("Problem with blocking user in data base by user id - " + id);
            log.error("Exception - " + ex);
        } finally {
            close(connection);
            close(pst);
        }
    }

    /**
     * Unblocking User by User identifier
     *
     * @param id User identifier
     */
    public static void unBlockUser(Integer id) {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_UN_BLOCK_USER);
            connection.setAutoCommit(false);
            pst.setInt(1, id);
            pst.executeUpdate();
            connection.commit();
            log.info("Unblock user in data base by user id - " + id);
        } catch (SQLException ex) {
            rollback(connection);
            log.error("Problem with unblocking user in data base by user id - " + id);
            log.error("Exception - " + ex);
        } finally {
            close(connection);
            close(pst);
        }
    }

    /**
     * Delete User by User identifier
     *
     * @param id User identifier
     */
    public static void deleteUser(Integer id) {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_DELETE_USER);
            connection.setAutoCommit(false);
            pst.setInt(1, id);
            pst.executeUpdate();
            connection.commit();
            log.info("Delete user in data base by user id - " + id);
        } catch (SQLException ex) {
            rollback(connection);
            log.error("Problem with deleting user in data base by user id - " + id);
            log.error("Exception - " + ex);
        } finally {
            close(connection);
            close(pst);
        }
    }

    /**
     * Closing PreparedStatement
     *
     * @param st PreparedStatement
     */
    private static void close(PreparedStatement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                log.error("Problem with closing preparedStatement");
                log.error("Exception - " + e);
            }
        }
    }

    /**
     * Closing Connection
     *
     * @param connection Connection
     */
    private static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Problem with closing connection");
                log.error("Exception - " + e);
            }
        }
    }

    /**
     * Rollback
     *
     * @param connection Connection
     */
    private static void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            log.error("Problem with rollback");
            log.error("Exception - " + ex);
        }
    }

}
