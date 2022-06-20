package com.gmail.KostiaBorozdyh.model.service;

import com.gmail.KostiaBorozdyh.model.dao.OrderDao;
import com.gmail.KostiaBorozdyh.model.entity.Order;
import com.gmail.KostiaBorozdyh.model.entity.User;
import com.gmail.KostiaBorozdyh.model.utils.Calculate;
import com.sun.jdi.ObjectReference;

import java.time.LocalDate;
import java.util.List;

public class OrderService {

    public static List<Order> getOrderListByCityOfArrival(String city) {
        return OrderDao.getOrderList(city);
    }

    public static void changeOrderPaymentStatusAfterConfirmByManager(Integer orderId) {
        Order order = OrderDao.getOrder(orderId);
        LocalDate dateOfArrival = Calculate.newArrivalTime(order.getDateCreate(), order.getDateOfArrival());
        OrderDao.changeOrderStatus(orderId, dateOfArrival);
    }

    public static void changeOrderPaymentStatusAfterUserPay(Integer orderId){
        OrderDao.changePayStatus(orderId);
    }

    public static void putOrderOnRecord(Integer orderId){
        OrderDao.putOnRecord(orderId);
    }

    public static void giveOrder(Integer orderId){
        OrderDao.giveOrder(orderId);
    }

    public static Order getOrderById(Integer orderId){
        return OrderDao.getOrder(orderId);
    }

    public static List<Order> getOrderListByUser(User user,boolean userHasRoleUser) {
        return (userHasRoleUser) ? OrderDao.getUserOrders(user) : OrderDao.getOrderList();
    }

    public static void save(Order order) {
        OrderDao.createOrder(order);
    }
}
