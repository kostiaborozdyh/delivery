package com.gmail.KostiaBorozdyh.model.utils;

import com.gmail.KostiaBorozdyh.model.dao.UserDao;
import com.gmail.KostiaBorozdyh.model.entity.User;
import com.gmail.KostiaBorozdyh.model.dto.ValidListDTO;

/**
 * Validation utils
 */
public class Validation {
    private static ValidListDTO validList;
    private static User user;

    /**
     * Valid user
     *
     * @param userCheck  User for validation
     * @param checkLogin boolean whether to check the login
     * @param checkEmail boolean whether to check the email
     * @return ValidListDTO
     */
    public static ValidListDTO valid(User userCheck, boolean checkLogin, boolean checkEmail) {
        validList = new ValidListDTO();
        user = userCheck;
        validList.init();

        if (checkLogin) {
            checkLogin();
        }

        if (checkEmail) {
            checkEmail();
        }

        checkFirstName();
        checkLastName();
        checkPhoneNumber();
        checkPassword();

        return validList;
    }

    /**
     * Check password
     * <p>
     * checking passwords to see if they are the same and valid
     */
    private static void checkPassword() {
        if (user.getPassword().equals(user.getSecondPassword())) {
            if (passwordValid(user.getPassword()) && !user.getPassword().equals("")) {
                validList.setInvalidPasswordName("InvalidPasswordName");
            }
        } else {
            validList.setInvalidPassword("InvalidPassword");
        }
    }

    /**
     * Check phoneNumber
     * <p>
     * if the phone is not empty then check it for validity otherwise it is valid
     */
    private static void checkPhoneNumber() {
        if (phoneNumberValid(user.getPhoneNumber()) || user.getPhoneNumber().equals("")) {
            validList.setValidPhoneNumber(user.getPhoneNumber());
        } else {
            validList.setInvalidPhoneNumber(user.getPhoneNumber());
        }
    }

    /**
     * Check firstName
     * <p>
     * checking first name for validity
     */
    private static void checkFirstName() {
        if (nameValid(user.getFirstName())) {
            validList.setValidFirstName(user.getFirstName());
        } else {
            validList.setInValidFirsName(user.getFirstName());
        }
    }

    /**
     * Check lastName
     * <p>
     * checking last name for validity
     */
    private static void checkLastName() {
        if (nameValid(user.getLastName())) {
            validList.setValidLastName(user.getLastName());
        } else {
            validList.setInvalidLastName(user.getLastName());
        }
    }

    /**
     * Check login
     * <p>
     * checking login for validity and uniqueness
     */
    private static void checkLogin() {
        if (UserDao.loginIsValid(user.getLogin())) {
            if (loginNameValid(user.getLogin())) {
                validList.setValidLoginName(user.getLogin());
            } else {
                validList.setInvalidLoginName(user.getLogin());
            }
        } else {
            validList.setInvalidLogin(user.getLogin());
        }
    }

    /**
     * Check email
     * <p>
     * checking email for validity and uniqueness
     */
    private static void checkEmail() {
        if (UserDao.emailIsValid(user.getEmail())) {
            if (emailNameValid(user.getEmail())) {
                validList.setValidEmailName(user.getEmail());
            } else {
                validList.setInvalidEmailName(user.getEmail());
            }
        } else {
            validList.setInvalidEmail(user.getEmail());
        }
    }

    /**
     * Count invalid fields
     *
     * @param validList ValidListDTO userValidList
     * @return true if all user fields are valid, otherwise false
     */
    public static boolean count(ValidListDTO validList) {
        int count = 0;
        if (validList.getInvalidEmail() != null) count++;
        if (validList.getInvalidLogin() != null) count++;
        if (validList.getInvalidPassword() != null) count++;
        if (validList.getInvalidEmailName() != null) count++;
        if (validList.getInvalidFirstName() != null) count++;
        if (validList.getInvalidLastName() != null) count++;
        if (validList.getInvalidLoginName() != null) count++;
        if (validList.getInvalidPasswordName() != null) count++;
        if (validList.getInvalidPhoneNumber() != null) count++;
        return count == 0;
    }

    public static boolean nameValid(String firstName) {
        return firstName.matches("^[a-zA-ZА-Яа-яЇїіІ]{4,20}");
    }

    public static boolean phoneNumberValid(String phoneNumber) {
        return phoneNumber.matches("\\+380\\d{9}") || phoneNumber.matches("0\\d{9}$");
    }

    public static boolean loginNameValid(String login) {
        return login.matches("^[a-zA-Z1-9]{4,20}");
    }

    public static boolean emailNameValid(String email) {
        return email.matches("^([a-z\\d_-]+\\.)*[a-z\\d_-]+@[a-z\\d_-]+(\\.[a-z\\d_-]+)*\\.[a-z]{2,6}$");
    }

    public static boolean passwordValid(String password) {
        return !password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$");
    }
}
