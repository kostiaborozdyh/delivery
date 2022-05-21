package com.example.demos.model;

import com.example.demos.DB.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDao {
    public static final String SQL_GET_CITY_NAME= "SELECT * FROM city_from c WHERE c.id=?";
    public static final String SQL_GET_CITY_ID= "SELECT * FROM city_from c WHERE c.city_from_name=?";
    public static String getCityName(int id) {
        String name = null;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_CITY_NAME)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    name = rs.getString("city_from_name");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return name;
    }

    public static Integer getCityID(String name) {
       Integer id = 0;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_CITY_ID)) {
            pst.setString(  1, name);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }
}
