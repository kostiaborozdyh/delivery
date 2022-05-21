package com.example.demos.model;

import com.example.demos.DB.DBHelper;

import java.sql.*;
import java.time.LocalDate;

public class Calculate {
    
    public static final String SQL_GET_CITY_DISTANCE= "SELECT dcf.city_from_name, dct.city_to_name, d.way_length\n" +
            "  FROM delivery.distance as d\n" +
            "  join delivery.city_from as dcf on  dcf.id = d.city_from_id  \n" +
            "  and dcf.city_from_name = ? \n" +
            "  join delivery.city_to as dct   on dct.id = d.city_to_id\n" +
            "  and dct.city_to_name = ?";

    public static Integer distance(String cityFrom, String cityTo) {
        int  distance = 0;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_CITY_DISTANCE)) {
            pst.setString(1, cityFrom);
            pst.setString(2, cityTo);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    distance = rs.getInt("way_length");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return distance;
    }

    public static Integer delivery(String cityFrom, String cityTo,String weight,String height, String length,String width) {
        int  distance;
        int result;
        distance = Calculate.distance(cityFrom,cityTo);
        result = distance*6*Integer.parseInt(weight)*Integer.parseInt(height)*
                Integer.parseInt(length)*Integer.parseInt(width)/100;
        return result;
    }
    public static Integer avgPrice(Integer distance){
        if (distance==0) return 60;
        return 6*distance/10;
    }
    public static Integer deliveryPrice(Integer distance,Integer volume, String weight){
        return avgPrice(distance)*Integer.parseInt(weight)*volume/10;
    }
    public static Integer deliveryInOneCity(String weight,String height, String length,String width) {
        int result;
        result = 10*Integer.parseInt(weight)*Integer.parseInt(height)*
                Integer.parseInt(length)*Integer.parseInt(width);
        return result;
    }
    public static Integer volume(String height,String length, String width){
        return Integer.parseInt(height)*Integer.parseInt(length)*Integer.parseInt(width);
    }
    public static LocalDate arrivalTime(Integer distance){

        if(distance==0) distance=1;
        else distance = distance / 100;
        return LocalDate.now().plusDays(distance);
    }
}
