package com.example.demos.model;

import com.example.demos.model.entity.Order;

public class CreateMessage {
    public static final String THEME_CREATE_ORDER ="Створення заявки";
    public static final String THEME_CHANGE_PAYMENT_STATUS ="Підтвердження заявки";
    public static final String THEME_SEND_ORDER="Заявка№";
    public static final String THEME_BLOCK_USER="Блокування акаунта";
    public static final String TEXT="Ви успішно оформили заявку на доставку вантажу з міста ";
    public static final String TEXT_CHANGE_PAYMENT_STATUS="Менеджер підтвердив вашу заявку на доставку вантажу, до слпати ";
    public static final String TEXT_BLOCK_USER="Ваш акаунт заблоковано по причині: ";
    public static final String DATE="Орієнтовна дата прибуття ";
    public static final String MONEY=". До сплати ";
    public static final String MANAGER="Ваша заявка передана на розгляд менеджеру.";
    public static String[] messageCreateOrder(String cityFrom, String cityTo, Integer distance,Integer price){
        String sb = TEXT + cityFrom + " в " + cityTo + ".\n" +
                DATE + Calculate.arrivalTime(distance) + MONEY + price + ".\n" +
                MANAGER;
        return new String[]{THEME_CREATE_ORDER, sb};
    }
    public static String[] messageChangePaymentStatus(Integer price){
        return new String[]{THEME_CHANGE_PAYMENT_STATUS, TEXT_CHANGE_PAYMENT_STATUS + price};
    }
  public static String[] messageSendOrder(Order order){
      String sbTheme = THEME_SEND_ORDER + order.getId();
      String sb = "Опис: " + order.getDescription() + "\n" +
              "Вага: " + order.getWeight() + "\n" +
              "Об'єм: " + order.getVolume() + "\n" +
              "Ціна: " + order.getPrice() + "\n" +
              "Місто відправлення: " + order.getCityFrom() + "\n" +
              "Місто прибуття: " + order.getCityTo() + "\n" +
              "Адреса: " + order.getAddress() + "\n" +
              "Дата створення: " + order.getDateCreate() + "\n" +
              "Дата прибуття: " + order.getDateOfArrival() + "\n"+
              "Статус: " + order.getPaymentStatus() + "\n";
        return new String[]{sbTheme, sb};

    }
    public static String[] blockUser(String reason){
        return new String[]{THEME_BLOCK_USER,TEXT_BLOCK_USER+reason};
    }
}
