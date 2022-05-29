package com.example.demos.model.dao;

import com.example.demos.DB.DBHelper;
import com.example.demos.model.utils.Calculate;
import com.example.demos.model.utils.JsonParser;
import com.example.demos.model.entity.Order;
import com.example.demos.model.entity.User;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class OrderDao {

    public static final String SQL_INSERT_ORDER = "INSERT INTO delivery.order(description,weight,volume,price,city_from,city_to,address,date_create,date_of_arrival,user_id,payment_status_id,location_status_id)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String SQL_GET_USER_ORDERS = "SELECT do.id,  do.description, do.weight, do.volume, do.price,\n" +
            "do.city_from, do.city_to, do.address, do.date_create, do.date_of_sending,\n" +
            "do.date_of_arrival, dp.status, du.login, dl.location\n" +
            "FROM delivery.order as do\n" +
            "join delivery.user as du on  do.user_id = du.id \n" +
            "join delivery.location_status as dl on  do.location_status_id = dl.id \n" +
            "join delivery.payment_status as dp  on  do.payment_status_id = dp.id and do.user_id=?";
    public static final String SQL_CHANGE_PAY_STATUS = "UPDATE delivery.order d SET d.payment_status_id = 3 WHERE d.id=?";
    public static final String SQL_CHANGE_ORDER_STATUS = "UPDATE delivery.order d SET d.payment_status_id = 2, d.location_status_id = 2, d.date_of_sending = ?, d.date_of_arrival=? WHERE d.id=?";
    public static final String SQL_GIVE_ORDER = "UPDATE delivery.order d SET d.location_status_id = 4 WHERE d.id=?";
    public static final String SQL_PUT_ON_RECORD = "UPDATE delivery.order d SET d.location_status_id = 3 WHERE d.id=?";
    public static final String SQL_GET_USER_ID = "SELECT * FROM delivery.order d WHERE d.id = ?";
    public static final String SQL_DELETE_ORDERS = "DELETE FROM delivery.order d WHERE d.user_id =?";
    public static final String SQL_GET_ORDER_LIST = "SELECT do.id,  do.description, do.weight, do.volume, do.price,\n" +
            "do.city_from, do.city_to, do.address, do.date_create, do.date_of_sending,\n" +
            "do.date_of_arrival, dp.status, du.login, dl.location\n" +
            "FROM delivery.order as do\n" +
            "join delivery.user as du on  do.user_id = du.id \n" +
            "join delivery.location_status as dl on  do.location_status_id = dl.id \n" +
            "join delivery.payment_status as dp  on  do.payment_status_id = dp.id";
    public static final String SQL_GET_ORDER = "SELECT do.id,  do.description, do.weight, do.volume, do.price,\n" +
            "do.city_from, do.city_to, do.address, do.date_create, do.date_of_sending,\n" +
            "do.date_of_arrival, dp.status, du.login, dl.location\n" +
            "FROM delivery.order as do\n" +
            "join delivery.user as du on  do.user_id = du.id \n" +
            "join delivery.location_status as dl on  do.location_status_id = dl.id \n" +
            "join delivery.payment_status as dp  on  do.payment_status_id = dp.id and do.id=?";
    public static final String SQL_GET_ORDER_LIST_BY_CITY = "SELECT do.id,  do.description, do.weight, do.volume, do.price,\n" +
            "do.city_from, do.city_to, do.address, do.date_create, do.date_of_sending,\n" +
            "do.date_of_arrival, dp.status, du.login, dl.location\n" +
            "FROM delivery.order as do\n" +
            "join delivery.user as du on  do.user_id = du.id \n" +
            "join delivery.location_status as dl on  do.location_status_id = dl.id \n" +
            "join delivery.payment_status as dp  on  do.payment_status_id = dp.id and do.city_to=? and do.location_status_id=?";
    public static final String SQL_GET_ORDER_LIST_BY_CITY_ON_RECORD = "SELECT do.id,  do.description, do.weight, do.volume, do.price,\n" +
            "do.city_from, do.city_to, do.address, do.date_create, do.date_of_sending,\n" +
            "do.date_of_arrival, dp.status, du.login, dl.location\n" +
            "FROM delivery.order as do\n" +
            "join delivery.user as du on  do.user_id = du.id \n" +
            "join delivery.location_status as dl on  do.location_status_id = dl.id \n" +
            "join delivery.payment_status as dp  on  do.payment_status_id = dp.id and do.city_to=? and do.location_status_id=? and do.date_of_arrival <= ?";

    public static void createOrder(String info, String cityFrom, String cityTo, String address, Integer price, Integer volume, String weight, Integer distance, Integer id) {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_INSERT_ORDER);
            connection.setAutoCommit(false);
            pst.setString(1, info);
            pst.setInt(2, Integer.parseInt(weight));
            pst.setInt(3, volume);
            pst.setInt(4, price);
            pst.setString(5, cityFrom);
            pst.setString(6, cityTo);
            pst.setString(7, address);
            pst.setDate(8, Date.valueOf(LocalDate.now()));
            pst.setDate(9, Date.valueOf(Calculate.arrivalTime(distance)));
            pst.setInt(10, id);
            pst.setInt(11, 1);
            pst.setInt(12, 1);
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

    public static List<Order> getUserOrders(User user) {
        List<Order> list = new ArrayList<>();
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_USER_ORDERS)) {
            pst.setInt(1, user.getId());
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Order order = getOneOrder(rs);
                    list.add(order);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static void changePayStatus(Integer id, Integer value, Integer money) {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_CHANGE_PAY_STATUS);
            connection.setAutoCommit(false);
            pst.setInt(1, id);
            UserDao.changeMoney(id, value, money);
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

    public static void changeOrderStatus(Integer id) {
        Order order = getOrder(id);
        LocalDate dateOfArrival = Calculate.newArrivalTime(order.getDateCreate(), order.getDateOfArrival());
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_CHANGE_ORDER_STATUS);
            connection.setAutoCommit(false);
            pst.setDate(1, Date.valueOf(LocalDate.now()));
            pst.setDate(2, Date.valueOf(dateOfArrival));
            pst.setInt(3, id);
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

    public static void giveOrder(Integer id) {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_GIVE_ORDER);
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
    }

    public static void putOnRecord(Integer id) {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_PUT_ON_RECORD);
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
    }

    public static Integer getUserId(Integer orderId) {
        int id = 0;
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_USER_ID)) {
            pst.setInt(1, orderId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("user_id");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public static List<Order> getOrderList() {
        List<Order> list = new ArrayList<>();
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_ORDER_LIST)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Order order = getOneOrder(rs);
                    list.add(order);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static Order getOrder(Integer orderId) {
        Order order = null;
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_ORDER)) {
            pst.setInt(1, orderId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    order = getOneOrder(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return order;
    }

    public static void deleteOrder(Integer userId) {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_DELETE_ORDERS);
            connection.setAutoCommit(false);
            pst.setInt(1, userId);
            pst.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            ex.printStackTrace();
        } finally {
            close(pst);
            close(connection);
        }
    }

    public static List<Order> getOrderList(String cityTo) {
        List<Order> list = new ArrayList<>();
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_ORDER_LIST_BY_CITY)) {
            pst.setString(1, JsonParser.cutName(cityTo));
            pst.setInt(2, 3);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Order order = getOneOrder(rs);
                    list.add(order);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static List<Order> getOrderListOnRecord(String cityTo) {
        List<Order> list = new ArrayList<>();
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_ORDER_LIST_BY_CITY_ON_RECORD)) {
            pst.setString(1, JsonParser.cutName(cityTo));
            pst.setInt(2, 2);
            pst.setDate(3, Date.valueOf(LocalDate.now()));
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Order order = getOneOrder(rs);
                    list.add(order);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static Order getOneOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setDescription(rs.getString("description"));
        order.setWeight(rs.getInt("weight"));
        order.setVolume(rs.getInt("volume"));
        order.setPrice(rs.getInt("price"));
        order.setCityFrom(rs.getString("city_from"));
        order.setCityTo(rs.getString("city_to"));
        order.setAddress(rs.getString("address"));
        order.setDateCreate(rs.getDate("date_create").toLocalDate());
        Date date = rs.getDate("date_of_sending");
        if (date == null) {
            order.setDateOfSending(null);
        } else {
            order.setDateOfSending(date.toLocalDate());
        }
        order.setDateOfArrival(rs.getDate("date_of_arrival").toLocalDate());
        order.setPaymentStatus(rs.getString("status"));
        order.setUserLogin(rs.getString("login"));
        order.setLocationStatus(rs.getString("location"));
        return order;
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
