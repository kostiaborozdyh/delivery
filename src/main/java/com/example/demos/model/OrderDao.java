package com.example.demos.model;

import com.example.demos.DB.DBHelper;
import com.example.demos.model.entity.Order;
import com.example.demos.model.entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    public static final String INSERT_ORDER = "INSERT INTO delivery.order(description,weight,volume,price,payment_status_id,address,date_create,date_of_arrival,user_id,city_from_id,city_to_id)  VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    public static final String GET_USER_ORDERS = "SELECT do.id, do.description, do.weight, do.volume, do.price,  \n" +
            "do.address, do.date_create, do.date_of_arrival, du.login, \n" +
            "dcf.city_from_name, dct.city_to_name, dp.status\n" +
            "  FROM delivery.order as do \n" +
            "  join delivery.user as du on  do.user_id = du.id and du.id=?\n" +
            "  join delivery.city_from as dcf on do.city_from_id = dcf.id\n" +
            "  join delivery.city_to as dct on  do.city_to_id = dct.id\n" +
            "  join delivery.payment_status as dp on  do.payment_status_id = dp.id order by dp.status DESC";
    public static final String CHANGE_PAY_STATUS = "UPDATE delivery.order d SET d.payment_status_id = 3 WHERE d.id=?";
    public static final String CHANGE_ORDER_STATUS = "UPDATE delivery.order d SET d.payment_status_id = 2 WHERE d.id=?";

    public static final String GET_USER_ID = "SELECT * FROM delivery.order d WHERE d.id = ?";
    public static final String GET_ORDER_LIST = "SELECT do.id,  do.description, do.weight, do.volume, do.price,\n" +
            "do.address, do.date_create, do.date_of_arrival, du.login, \n" +
            "  dcf.city_from_name, dct.city_to_name, dp.status\n" +
            "  FROM delivery.order as do \n" +
            "  join delivery.user as du on  do.user_id = du.id \n" +
            "  join delivery.city_from as dcf on do.city_from_id = dcf.id\n" +
            "  join delivery.city_to as dct on  do.city_to_id = dct.id\n" +
            "  join delivery.payment_status as dp on  do.payment_status_id = dp.id order by dp.status";
    public static final String GET_ORDER = "SELECT do.id,  do.description, do.weight, do.volume, do.price,\n" +
            "do.address, do.date_create, do.date_of_arrival, du.login, \n" +
            "  dcf.city_from_name, dct.city_to_name, dp.status\n" +
            "  FROM delivery.order as do \n" +
            "  join delivery.user as du on  do.user_id = du.id and do.id = ?\n" +
            "  join delivery.city_from as dcf on do.city_from_id = dcf.id\n" +
            "  join delivery.city_to as dct on  do.city_to_id = dct.id\n" +
            "  join delivery.payment_status as dp on  do.payment_status_id = dp.id order by dp.status";
    public static boolean createOrder(String info, String cityFrom, String cityTo, String address,Integer volume, Integer price, String weight, Integer id) {
        int count = 0;
        try( Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement  st = connection.prepareStatement(INSERT_ORDER)) {
            st.setString(1,info);
            st.setInt(2,Integer.parseInt(weight));
            st.setInt(3,volume);
            st.setInt(4,price);
            st.setInt(5,1);
            st.setString(6,address);
            st.setDate(7,Date.valueOf(LocalDate.now()));
            st.setDate(8,Date.valueOf(Calculate.arrivalTime(cityFrom,cityTo)));
            st.setInt(9,id);
            st.setInt(10,CityDao.getCityID(cityFrom));
            st.setInt(11,CityDao.getCityID(cityTo));
            count = st.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count>0;
    }

    public static List<Order> getUserOrders(User user){
        List<Order> list = new ArrayList<>();

        try( Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement  st = connection.prepareStatement(GET_USER_ORDERS)) {
            st.setInt(1,user.getId());
            try (ResultSet rs = st.executeQuery()) {
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

    public static boolean changePayStatus(Integer id, Integer value, Integer money){
        int count = 0;

        try( Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement  st = connection.prepareStatement(CHANGE_PAY_STATUS)) {
            st.setInt(1,id);
            UserDao.changeMoney(id,value,money);
            count = st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count>0;
    }
    public static boolean changeOrderStatus(Integer id){
        int count = 0;

        try( Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement  st = connection.prepareStatement(CHANGE_ORDER_STATUS)) {
            st.setInt(1,id);
            count = st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count>0;
    }
    public static Integer getUserId(Integer orderId){
        int id = 0;

        try( Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement  st = connection.prepareStatement(GET_USER_ID)) {
            st.setInt(1,orderId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    id=rs.getInt("user_id");
                }
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }



    public static List<Order> getOrderList(){
        List<Order> list = new ArrayList<>();

        try( Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement  st = connection.prepareStatement(GET_ORDER_LIST)) {
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Order order  = getOneOrder(rs);
                    list.add(order);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public static Order getOrder(Integer orderId){
        Order order=null;
        try( Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement  st = connection.prepareStatement(GET_ORDER)) {
            st.setInt(1,orderId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                   order = getOneOrder(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return order;
    }

    public static Order getOneOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setDescription(rs.getString("description"));
        order.setWeight(rs.getInt("weight"));
        order.setVolume(rs.getInt("volume"));
        order.setPrice(rs.getInt("price"));
        order.setAddress(rs.getString("address"));
        order.setPaymentStatus(rs.getString("status"));
        order.setDateCreate(rs.getDate("date_create").toLocalDate());
        order.setDateOfArrival(rs.getDate("date_of_arrival").toLocalDate());
        order.setCityFrom(rs.getString("city_from_name"));
        order.setCityTo(rs.getString("city_to_name"));
        order.setUserLogin(rs.getString("login"));
        return order;
    }
}
