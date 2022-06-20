package com.gmail.KostiaBorozdyh.model.service;

import com.gmail.KostiaBorozdyh.model.dao.OrderDao;
import com.gmail.KostiaBorozdyh.model.dao.UserDao;
import com.gmail.KostiaBorozdyh.model.entity.InfoTable;
import com.gmail.KostiaBorozdyh.model.entity.Order;
import com.gmail.KostiaBorozdyh.model.entity.User;
import com.gmail.KostiaBorozdyh.model.utils.CreateMessage;
import com.gmail.KostiaBorozdyh.model.utils.SendEmail;

public class UserService {

    public static void save(User user) {
        UserDao.insertUser(user);
    }

    public static void blockUser(Integer userId) {
        UserDao.blockUser(userId);
        String email = UserDao.getUserEmailByUserId(userId);
        SendEmail.send(email, CreateMessage.blockUser());
    }
    public static void unblockUser(Integer userId) {
        UserDao.unBlockUser(userId);
        String email = UserDao.getUserEmailByUserId(userId);
        SendEmail.send(email, CreateMessage.unBlockUser());
    }

    public static void deleteUser(Integer userId) {
        OrderDao.deleteOrderByUserId(userId);
        String email = UserDao.getUserEmailByUserId(userId);
        UserDao.deleteUser(userId);
        SendEmail.send(email, CreateMessage.deleteUser());
    }

    public static User editUser(User user) {
        return UserDao.editUser(user);
    }

    public static void changePassword(String email, String password) {
        UserDao.changePassword(email, password);
    }

    public static boolean emailIsValid(String email){
        return UserDao.emailIsValid(email);
    }

    public static boolean loginIsValid(String login){
        return UserDao.loginIsValid(login);
    }

    public static String getUserEmailByUserLogin(String login){
        return UserDao.getUserEmailByUserLogin(login);
    }

    public static User getUserByLoginAndPassword(String userLogin, String userPassword) {
        return UserDao.userValid(userLogin, userPassword);
    }

    public static void changeMoney(User user) {
        String login = user.getLogin();
        Integer money = user.getMoney();
        UserDao.changeMoney(login, money);
    }

    public static String getUserEmailByOrderId(Integer orderId) {
        Integer userId = OrderDao.getUserId(orderId);
        return UserDao.getUserEmailByUserId(userId);
    }

    public static void sendEmailByOrderId(Integer orderId, Integer function) {
        Integer userId = OrderDao.getUserId(orderId);
        boolean hasUserNotify = UserDao.getUserNotify(userId);

        if (hasUserNotify) {
            Order order = OrderDao.getOrder(orderId);
            String email = UserDao.getUserEmailByUserLogin(order.getUserLogin());

            switch (function){
                case 1: SendEmail.send(email, CreateMessage.messageChangePaymentStatus(order.getPrice())); break;
                case 2: SendEmail.send(email, CreateMessage.putOnRecord(orderId)); break;
                case 3: SendEmail.send(email, CreateMessage.messageSendOrder(order)); break;
            }
        }
    }

    public static void sendEmailAfterCreateOrder(User user, InfoTable infoTable) {
        boolean hasUserNotify = user.getNotify().equals("yes");
        if (hasUserNotify) {
            String email = user.getEmail();
            SendEmail.send(email, CreateMessage.messageCreateOrder(infoTable));
        }
    }
}
