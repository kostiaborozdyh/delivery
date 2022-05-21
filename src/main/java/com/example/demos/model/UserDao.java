package com.example.demos.model;

import com.example.demos.DB.DBHelper;
import com.example.demos.model.entity.User;
import com.example.demos.model.entity.ValidList;
import com.example.demos.security.Security;

import java.sql.*;

public class UserDao {
    public static final String SQL_GET_USER_VALID = "SELECT * FROM user u WHERE u.login=? AND u.password=?";
    public static final String SQL_GET_USER = "SELECT * FROM user u WHERE u.login=?";
    public static final String SQL_GET_USER_FROM_ID = "SELECT * FROM user u WHERE u.id=?";
    public static final String SQL_GET_EMAIL = "SELECT * FROM user u WHERE u.email=?";
    public static final String SQL_INSERT_USER = "INSERT INTO user(login,password,first_name,last_name,phone_number,email,role_id,notify)  VALUES (?,?,?,?,?,?,?,?)";
    public static final String SQL_CHANGE_MONEY = "UPDATE delivery.user d SET d.money = ? WHERE d.id=?";
    public static final String SQL_GET_USER_EMAIL = "SELECT * FROM user u WHERE u.login=?";
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
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_USER_VALID)) {
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
    public static boolean loginIsValid(String login){
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
        return (userLogin==null);
    }
    public static boolean emailIsValid(String email){
        String userEmail = null;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_EMAIL)) {
            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    userEmail= rs.getString("email");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (userEmail==null);
    }
    public static boolean firstNameValid(String firstName){
        return firstName.matches("^[a-zA-ZА-Яа-яЇїіІ]{4,20}");
    }
    public static boolean lastNameValid(String lastName){
        return lastName.matches("^[a-zA-ZА-Яа-яЇїіІ]{4,20}");
    }
    public static boolean phoneNumberValid(String phoneNumber){
        return phoneNumber.matches("\\+380\\d{9}") || phoneNumber.matches("0\\d{9}$");
    }
    public static boolean loginNameValid(String login){
        return login.matches("^[a-zA-Z]{4,20}");
    }
    public static boolean emailNameValid(String email){
        return email.matches("^([a-z\\d_-]+\\.)*[a-z\\d_-]+@[a-z\\d_-]+(\\.[a-z\\d_-]+)*\\.[a-z]{2,6}$");
    }
    public static boolean passwordValid(String password){
        return password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$");
    }


    public static boolean insertUser(User user) {
        int count = 0;
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(SQL_INSERT_USER))
        {
            st.setString(1,user.getLogin());
            st.setString(2,Security.hashPassword(user.getPassword()));
            st.setString(3,user.getFirstName());
            st.setString(4,user.getLastName());
            st.setString(5,user.getPhoneNumber());
            st.setString(6,user.getEmail());
            st.setInt(7,1);
            st.setString(8,user.getNotify());
            count = st.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count>0;
    }


    public static void changeMoney(Integer orderId,Integer value,Integer money) {
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(SQL_CHANGE_MONEY))
        {
            st.setInt(1,money-value);
            st.setInt(2,OrderDao.getUserId(orderId));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static boolean changePassword(String email, String password) {
        int count = 0;
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(SQL_CHANGE_PASSWORD))
        {
            st.setString(1,Security.hashPassword(password));
            st.setString(2,email);
            count = st.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count>0;
    }
    public static boolean refillMoney(Integer userId,Integer value,Integer money) {
        int count = 0;
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(SQL_CHANGE_MONEY))
        {
            st.setInt(1,money+value);
            st.setInt(2,userId);
            count = st.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count>0;
    }
    public static User editUser(User user) {
        int count = 0;
        PreparedStatement st = null;
        try (Connection connection = DBHelper.getInstance().getConnection())
        {
            if(user.getPassword().equals("Password1")) {
                st = connection.prepareStatement(SQL_EDIT_USER);
                st.setString(1,user.getFirstName());
                st.setString(2,user.getLastName());
                st.setString(3,user.getPhoneNumber());
                st.setString(4,user.getEmail());
                st.setString(5,user.getNotify());
                st.setInt(6,user.getId());

            }
            else {
                st = connection.prepareStatement(SQL_EDIT_USER_AND_PASSWORD);
                st.setString(1, Security.hashPassword(user.getPassword()));
                st.setString(2,user.getFirstName());
                st.setString(3,user.getLastName());
                st.setString(4,user.getPhoneNumber());
                st.setString(5,user.getEmail());
                st.setString(6,user.getNotify());
                st.setInt(7,user.getId());

            }
            count = st.executeUpdate();
            user.setPassword("");

        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                st.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(count>0) return user;
        else return null;
    }

    public static String getUserEmail(String login){
        String email = null;
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_USER_EMAIL))
        {
            pst.setString(1,login);
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


    public static ValidList valid(User user, String password,int ch){
        ValidList validList = new ValidList();
        validList.init();
        if (ch==1) {
            if (UserDao.loginIsValid(user.getLogin())) {
                if (UserDao.loginNameValid(user.getLogin())) validList.setValidLoginName(user.getLogin());
                else validList.setInvalidLoginName(user.getLogin());
            } else validList.setInvalidLogin(user.getLogin());
        }
        if (ch==3 || ch==1) {
            if (UserDao.emailIsValid(user.getEmail())) {
                if (UserDao.emailNameValid(user.getEmail())) validList.setValidEmailName(user.getEmail());
                else validList.setInvalidEmailName(user.getEmail());
            } else validList.setInvalidEmail(user.getEmail());
        }
        if (UserDao.firstNameValid(user.getFirstName())) validList.setValidFirstName(user.getFirstName());
        else  validList.setInValidFirsName(user.getFirstName());
        if (UserDao.lastNameValid(user.getLastName())) validList.setValidLastName(user.getLastName());
        else  validList.setInvalidLastName(user.getLastName());
        if (UserDao.phoneNumberValid(user.getPhoneNumber()) || user.getPhoneNumber().equals("")) validList.setValidPhoneNumber(user.getPhoneNumber());
        else validList.setInvalidPhoneNumber(user.getPhoneNumber());
        if (user.getPassword().equals(password)) {
            if(!UserDao.passwordValid(password)) validList.setInvalidPasswordName("InvalidPasswordName");
        }
        else validList.setInvalidPassword("InvalidPassword");
        return validList;
    }
    public static boolean validation(ValidList validList){
        int count = 0;
        if(validList.getInvalidEmail()!=null) count++;
        if(validList.getInvalidLogin()!=null) count++;
        if(validList.getInvalidPassword()!=null) count++;
        if(validList.getInvalidEmailName()!=null) count++;
        if(validList.getInvalidFirstName()!=null) count++;
        if(validList.getInvalidLastName()!=null) count++;
        if(validList.getInvalidLoginName()!=null) count++;
        if(validList.getInvalidPasswordName()!=null) count++;
        if(validList.getInvalidPhoneNumber()!=null) count++;
        return count==0;
    }

}
