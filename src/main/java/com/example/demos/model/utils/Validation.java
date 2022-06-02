package com.example.demos.model.utils;

import com.example.demos.model.dao.UserDao;
import com.example.demos.model.entity.User;
import com.example.demos.model.entity.ValidList;

public class Validation {
    private static ValidList validList;
    private static User user;

    public static ValidList valid(User userCheck, boolean checkLogin, boolean checkEmail) {
        validList = new ValidList();
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

    private static void checkPassword() {
        if (user.getPassword().equals(user.getSecondPassword())) {
            if (passwordValid(user.getPassword()) && !user.getPassword().equals("")) {
                validList.setInvalidPasswordName("InvalidPasswordName");
            }
        } else {
            validList.setInvalidPassword("InvalidPassword");
        }
    }

    private static void checkPhoneNumber() {
        if (phoneNumberValid(user.getPhoneNumber()) || user.getPhoneNumber().equals("")) {
            validList.setValidPhoneNumber(user.getPhoneNumber());
        } else {
            validList.setInvalidPhoneNumber(user.getPhoneNumber());
        }
    }

    private static void checkFirstName() {
        if (nameValid(user.getFirstName())) {
            validList.setValidFirstName(user.getFirstName());
        } else {
            validList.setInValidFirsName(user.getFirstName());
        }
    }

    private static void checkLastName() {
        if (nameValid(user.getLastName())) {
            validList.setValidLastName(user.getLastName());
        } else {
            validList.setInvalidLastName(user.getLastName());
        }
    }


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

    public static boolean count(ValidList validList) {
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
