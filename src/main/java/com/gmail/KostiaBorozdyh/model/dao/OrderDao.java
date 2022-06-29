package com.gmail.KostiaBorozdyh.model.dao;

import com.gmail.KostiaBorozdyh.DB.DBHelper;
import com.gmail.KostiaBorozdyh.model.utils.Calculate;
import com.gmail.KostiaBorozdyh.model.utils.JsonParser;
import com.gmail.KostiaBorozdyh.model.entity.Order;
import com.gmail.KostiaBorozdyh.model.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
/**
 * Data access object for Order entity
 */
public class OrderDao {
    private static final Logger log = Logger.getLogger(OrderDao.class);

    public static final String SQL_INSERT_ORDER = "INSERT INTO delivery.order(description,weight,volume,price,city_from,city_to,address,date_create,date_of_arrival,user_id,payment_status_id,location_status_id)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String SQL_CHANGE_PAY_STATUS = "UPDATE delivery.order d SET d.payment_status_id = 3 WHERE d.id=?";
    public static final String SQL_CHANGE_ORDER_STATUS = "UPDATE delivery.order d SET d.payment_status_id = 2, d.location_status_id = 2, d.date_of_sending = ?, d.date_of_arrival=? WHERE d.id=?";
    public static final String SQL_GIVE_ORDER = "UPDATE delivery.order d SET d.location_status_id = 4 WHERE d.id=?";
    public static final String SQL_PUT_ON_RECORD = "UPDATE delivery.order d SET d.location_status_id = 3 WHERE d.id=?";
    public static final String SQL_DELETE_ORDERS = "DELETE FROM delivery.order d WHERE d.user_id =?";
    public static final String SQL_GET_USER_ORDERS = "SELECT do.id,  do.description, do.weight, do.volume, do.price,\n" +
            "do.city_from, do.city_to, do.address, do.date_create, do.date_of_sending,\n" +
            "do.date_of_arrival, dp.status, du.login, dl.location\n" +
            "FROM delivery.order as do\n" +
            "join delivery.user as du on  do.user_id = du.id \n" +
            "join delivery.location_status as dl on  do.location_status_id = dl.id \n" +
            "join delivery.payment_status as dp  on  do.payment_status_id = dp.id and do.user_id=?";
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

    /**
     * Creating new order
     *
     * @param order Order
     */
    public static void createOrder(Order order) {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_INSERT_ORDER);
            connection.setAutoCommit(false);
            pst.setString(1, order.getDescription());
            pst.setInt(2, order.getWeight());
            pst.setInt(3, order.getVolume());
            pst.setInt(4, order.getPrice());
            pst.setString(5, order.getCityFrom());
            pst.setString(6, order.getCityTo());
            pst.setString(7, order.getAddress());
            pst.setDate(8, Date.valueOf(LocalDate.now()));
            pst.setDate(9, Date.valueOf(Calculate.arrivalTime(order.getDistance())));
            pst.setInt(10, order.getUserId());
            pst.setInt(11, 1);
            pst.setInt(12, 1);
            pst.executeUpdate();
            connection.commit();
            log.info("add order to data base with description - "+order.getDescription());
        } catch (SQLException ex) {
            log.error("problem with adding order to data base with description -  "+order.getDescription());
            log.error("Exception -  "+ex);
            rollback(connection);
        } finally {
            close(connection);
            close(pst);
        }

    }

    /**
     * Return orderList by User identifier
     *
     * @param user
     * @return List of Order items. If any problems returns empty list.
     */
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
            log.info("get list order from data base by user login - " + user.getLogin());
        } catch (SQLException ex) {
            log.error("problem with getting list order from data base by user login -  "+ user.getLogin());
            log.error("Exception - "+ex);
        }
        return list;
    }

    /**
     * Update order Pay Status by Order identifier
     *
     * @param id Order identifier
     */
    public static void changePayStatus(Integer id) {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_CHANGE_PAY_STATUS);
            connection.setAutoCommit(false);
            pst.setInt(1, id);
            pst.executeUpdate();
            connection.commit();
            log.info("change pay status for order by orderId - "+id);
        } catch (SQLException ex) {
            log.error("problem with changing pay status for order by orderId -  "+id);
            log.error("Exception - "+ ex);
            rollback(connection);
        } finally {
            close(connection);
            close(pst);
        }
    }

    /**
     * Update order Pay Status, Location Status, Date of Sending, Date of arrival by Order identifier
     *
     * @param id Order identifier
     * @param dateOfArrival LocalDate
     */
    public static void changeOrderStatus(Integer id,LocalDate dateOfArrival) {
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
            log.info("confirm order by orderId - "+id);
        } catch (SQLException ex) {
            log.error("problem with confirming order by orderId - " + id);
            log.error("Exception - "+ ex);
            rollback(connection);
        } finally {
            close(connection);
            close(pst);
        }
    }

    /**
     * Update order Location Status by Order identifier(order.location_status_id=4)
     *
     * @param id Order identifier
     */
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
            log.info("Give order to user by orderId - " + id);
        } catch (SQLException ex) {
            log.error("problem with giving order to user by orderId - " + id);
            log.error("Exception - "+ ex);
            rollback(connection);
        } finally {
            close(connection);
            close(pst);
        }
    }

    /**
     * Update order Location Status by Order identifier(order.location_status_id=3)
     *
     * @param id Order identifier
     */
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
            log.info("Put on record order by orderId - " + id);
        } catch (SQLException ex) {
            log.error("problem with putting on record order by orderId - " + id);
            log.error("Exception - "+ ex);
            rollback(connection);
        } finally {
            close(connection);
            close(pst);
        }
    }

    /**
     * Return all orders
     *
     * @return List of Order items. If any problems returns empty list.
     */
    public static List<Order> getOrderList() {
        List<Order> list = new ArrayList<>();
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_ORDER_LIST)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Order order = getOneOrder(rs);
                    list.add(order);
                }
                log.info("Get all orders from database");
            }
        } catch (SQLException ex) {
            log.error("Problem with getting all orders from database");
            log.error("Exception - "+ ex);
        }
        return list;
    }

    /**
     * Return order by Order identifier
     *
     * @param orderId Order identifier
     *
     * @return Order entity or null if wasn't found
     */
    public static Order getOrder(Integer orderId) {
        Order order = null;
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_ORDER)) {
            pst.setInt(1, orderId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    order = getOneOrder(rs);
                }
                log.info("Get order from database by orderId - "+orderId);
            }
        } catch (SQLException ex) {
            log.error("Problem with getting order from database by orderId - "+orderId);
            log.error("Exception - "+ ex);
        }
        return order;
    }

    /**
     * Delete order by User identifier
     *
     * @param userId User identifier
     *
     */
    public static void deleteOrderByUserId(Integer userId) {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_DELETE_ORDERS);
            connection.setAutoCommit(false);
            pst.setInt(1, userId);
            pst.executeUpdate();
            connection.commit();
            log.info("Delete all orders from data base by userId - "+userId);
        } catch (SQLException ex) {
            log.error("Problem with deleting orders from data base by userId - "+userId);
            log.error("Exception - "+ ex);
            rollback(connection);
        } finally {
            close(pst);
            close(connection);
        }
    }

    /**
     * Return orderList by cityTo
     *
     * @param cityTo
     * @return List of Order items. If any problems returns empty list.
     */
    public static List<Order> getOrderList(String cityTo) {
        List<Order> list = new ArrayList<>();
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_ORDER_LIST_BY_CITY)) {
            pst.setString(1, JsonParser.cutCityName(cityTo));
            pst.setInt(2, 3);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Order order = getOneOrder(rs);
                    list.add(order);
                }
                log.info("Get list order from data base by cityTo - "+cityTo);
            }
        } catch (SQLException ex) {
            log.error("Problem with getting list order from data base by cityTo - "+cityTo);
            log.error("Exception - "+ ex);
        }
        return list;
    }

    /**
     * Return orderList by cityTo and LocaleDate.now() and Location Status
     *
     * @param cityTo
     * @return List of Order items. If any problems returns empty list.
     */
    public static List<Order> getOrderListOnRecord(String cityTo) {
        List<Order> list = new ArrayList<>();
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement pst = connection.prepareStatement(SQL_GET_ORDER_LIST_BY_CITY_ON_RECORD)) {
            pst.setString(1, JsonParser.cutCityName(cityTo));
            pst.setInt(2, 2);
            pst.setDate(3, Date.valueOf(LocalDate.now()));
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Order order = getOneOrder(rs);
                    list.add(order);
                }
            }
            log.info("Get orders from data base by cityTo - "+cityTo+", and dat eof arrival - "+LocalDate.now());
        } catch (SQLException ex) {
            log.error("Problem with getting orders  from data base by cityTo - "+cityTo+", and dat eof arrival - "+LocalDate.now());
            log.error("Exception - "+ ex);
        }
        return list;
    }

    /**
     * Extracts Order.Record from the result set row.
     */
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
                log.error("Exception - "+ e);
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
                log.error("Exception - "+ e);
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
            log.error("Exception - "+ ex);
        }
    }

}
