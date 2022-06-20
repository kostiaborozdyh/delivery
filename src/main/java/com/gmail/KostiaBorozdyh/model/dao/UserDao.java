package com.gmail.KostiaBorozdyh.model.dao;

import com.gmail.KostiaBorozdyh.DB.DBHelper;
import com.gmail.KostiaBorozdyh.model.entity.User;
import com.gmail.KostiaBorozdyh.model.utils.Validation;
import com.gmail.KostiaBorozdyh.security.Security;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public static final String SQL_EDIT_USER = "UPDATE delivery.user d \n" +
            "SET d.first_name = ?, d.last_name = ?, \n" +
            "d.phone_number = ?, d.email = ?, d.notify = ?\n" +
            "WHERE d.id=?";
    public static final String SQL_EDIT_USER_AND_PASSWORD = "UPDATE delivery.user d \n" +
            "SET d.password = ?, d.first_name = ?, d.last_name = ?, \n" +
            "d.phone_number = ?, d.email = ?,  d.notify = ?\n" +
            "WHERE d.id=?";

    public static User userValid(String login, String password) {
        log.info("Перевірка користувача " + login);
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
            log.info("Перевірка користувача " + login + " завершено");
        } catch (Exception ex) {
            log.error("Помилка, перевірка користувача" + ex);
        } finally {
            close(pst);
        }
        return user;
    }

    public static boolean getUserNotify(Integer id) {
        log.info("Перевірка користувача з id " + id + " на підписку");
        String notify = null;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_USER_FROM_ID)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    notify = rs.getString("notify");
                }
            }
            log.info("Перевірка користувача з id " + id + " на підписку завершено");
        } catch (SQLException ex) {
            log.error("Помилка,Перевірка користувача з id " + id + " на підписку" + ex);
        }
        return (notify.equals("yes"));
    }

    public static boolean loginIsValid(String login) {
        log.info("Перевріка чи існує логін " + login);
        String userLogin = null;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_USER)) {
            pst.setString(1, login);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    userLogin = rs.getString("login");
                }
            }
            log.info("Перевріка чи існує логін " + login + " завершено");
        } catch (SQLException ex) {
            log.error("Помилка, перевріка чи існує логін " + login + ex);
        }
        return (userLogin == null);
    }

    public static boolean emailIsValid(String email) {
        log.info("Перевріка чи існує email " + email);
        String userEmail = null;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_EMAIL)) {
            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    userEmail = rs.getString("email");
                }
            }
            log.info("Перевріка чи існує email " + email + " завершено");
        } catch (SQLException ex) {
            log.error("Помилка, перевріка чи існує email " + email + ex);
        }
        return (userEmail == null);
    }


    public static boolean insertUser(User user) {
        log.info("Вставлення юзера " + user.getLogin());
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
            log.info("Вставлення юзера " + user.getLogin() + " завершено");
        } catch (Exception ex) {
            rollback(connection);
            log.error("Помилка, вставлення юзера " + user.getLogin() + ex);
        } finally {
            close(connection);
            close(pst);
        }
        return count > 0;
    }

    public static void changeMoney(String login, Integer money) {
        log.info("Зняття грошей у юзера з login " + login);
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
            log.info("Зняття грошей у юзера з login " + login + " завершено");
        } catch (SQLException ex) {
            rollback(connection);
            log.error("Помилка, зняття грошей у юзера з login " + login + ex);
        } finally {
            close(connection);
            close(pst);
        }
    }

    public static boolean changePassword(String email, String password) {
        log.info("Заміна паролю для юзера " + email);
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
            log.info("Заміна паролю для юзера " + email + " завершено");
        } catch (Exception ex) {
            rollback(connection);
            log.error("Помилка, заміна паролю для юзера " + email + ex);
        } finally {
            close(connection);
            close(pst);
        }
        return count > 0;
    }

    public static User editUser(User user) {
        log.info("Редагування користувача з логіном " + user.getLogin());
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
            log.info("Редагування користувача з логіном " + user.getLogin() + " завершено");
        } catch (Exception ex) {
            rollback(connection);
            log.error("Помилка, редагування користувача з логіном " + user.getLogin() + ex);
        } finally {
            close(connection);
            close(pst);
        }
        if (count > 0) {
            return user;
        }
        return null;
    }

    public static String getUserEmailByUserLogin(String login) {
        log.info("Вибірка email юзера по логіну " + login);
        String email = null;
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_USER_EMAIL)) {
            pst.setString(1, login);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    email = rs.getString("email");
                }

            }
            log.info("Вибірка email юзера по логіну " + login + " завершено");
        } catch (SQLException ex) {
            log.error("Помилка, вибірка email юзера по логіну " + login + ex);
        }
        return email;
    }

    public static String getUserEmailByUserId(Integer id) {
        log.info("Вибірка email юзера по id " + id);
        String email = null;
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_USER_EMAIL_BY_ID)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    email = rs.getString("email");
                }
            }
            log.info("Вибірка email юзера по id " + id + " завершено");
        } catch (SQLException ex) {
            log.error("Помилка, вибірка email юзера по id " + id + ex);
        }
        return email;
    }

    public static List<User> getUsers(int skip) {
        log.info("Вибірка всіх юзерів");
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
            log.info("Вибірка всіх юзерів зевершено");
        } catch (SQLException ex) {
            log.error("Помилка, вибірка всіх юзерів" + ex);
        }
        return userList;
    }

    public static Integer getUserCount() {
        log.info("Вибірка кількості юзерів");
        int count = 0;
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_USER_COUNT)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    count = rs.getInt("count(1)");
                }
            }
            log.info("Вибірка кількості юзерів зевершено");
        } catch (SQLException ex) {
            log.error("Помилка, вибірка кількості юзерів" + ex);
        }
        return count;
    }


    public static void blockUser(Integer id) {
        log.info("Блокування юзера по id " + id);
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_BLOCK_USER);
            connection.setAutoCommit(false);
            pst.setInt(1, id);
            pst.executeUpdate();
            connection.commit();
            log.info("Блокування юзера по id " + id + " завершено");
        } catch (SQLException ex) {
            rollback(connection);
            log.error("Помилка, блокування юзера по id" + id + ex);
        } finally {
            close(connection);
            close(pst);
        }
    }

    public static void unBlockUser(Integer id){
        log.info("Розблокування юзера по id " + id);
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_UN_BLOCK_USER);
            connection.setAutoCommit(false);
            pst.setInt(1, id);
            pst.executeUpdate();
            connection.commit();
            log.info("Розблокування юзера по id " + id + " завершено");
        } catch (SQLException ex) {
            rollback(connection);
            log.error("Помилка, розблокування юзера по id" + id + ex);
        } finally {
            close(connection);
            close(pst);
        }
    }

    public static void deleteUser(Integer id) {
        log.info("Видалення юзера по id " + id);
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_DELETE_USER);
            connection.setAutoCommit(false);
            pst.setInt(1, id);
            pst.executeUpdate();
            connection.commit();
            log.info("Видалення юзера по id " + id + " завершено");
        } catch (SQLException ex) {
            rollback(connection);
            log.error("Помилка, видалення юзера по id" + id + ex);
        } finally {
            close(connection);
            close(pst);
        }
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
