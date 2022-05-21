package com.example.demos.model;

import com.example.demos.DB.DBHelper;
import com.example.demos.model.entity.Distance;
import com.example.demos.model.entity.InfoTable;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InfoTableDao {
    public static final String SQL_GET_INFO_TABLE= " SELECT dcf.city_from_name, dct.city_to_name, d.way_length\n" +
            "  FROM delivery.distance as d\n" +
            "  join delivery.city_from as dcf on  dcf.id = d.city_from_id  \n" +
            "  and dcf.city_from_name = ? \n" +
            "  join delivery.city_to as dct   on dct.id = d.city_to_id";
    public static final String SQL_GET_INFO_TABLE_AND_DISTANCE= " SELECT dcf.city_from_name, dct.city_to_name, d.way_length\n" +
            "  FROM delivery.distance as d\n" +
            "  join delivery.city_from as dcf on  dcf.id = d.city_from_id  \n" +
            "  and dcf.city_from_name = ? \n" +
            "  and d.way_length > ? \n" +
            "  and d.way_length < ?\n" +
            "  join delivery.city_to as dct   on dct.id = d.city_to_id";
    public static final String SQL_GET_INFO_TABLE_WITH_MAX_DISTANCE= " SELECT dcf.city_from_name, dct.city_to_name, d.way_length\n" +
            "  FROM delivery.distance as d\n" +
            "  join delivery.city_from as dcf on  dcf.id = d.city_from_id  \n" +
            "  and dcf.city_from_name = ? \n" +
            "  and d.way_length < ? \n" +
            "  join delivery.city_to as dct   on dct.id = d.city_to_id";
    public static final String SQL_GET_INFO_TABLE_WITH_MIN_DISTANCE= "SELECT dcf.city_from_name, dct.city_to_name, d.way_length\n" +
            "  FROM delivery.distance as d\n" +
            "  join delivery.city_from as dcf on  dcf.id = d.city_from_id  \n" +
            "  and dcf.city_from_name = ? \n" +
            "  and d.way_length > ?\n" +
            "  join delivery.city_to as dct   on dct.id = d.city_to_id";
    public static List<InfoTable> getInfoTable(String name, String minPrice, String maxPrice, String minDistance, String maxDistance) {
        List<InfoTable> table = new ArrayList<>();
        PreparedStatement pst = null;
        try (Connection con = DBHelper.getInstance().getConnection()) {
            if((Objects.equals(minDistance, "")) && (Objects.equals(maxDistance, ""))) {
                pst = con.prepareStatement(SQL_GET_INFO_TABLE);
                pst.setString(1, name);
            }
            if((Objects.equals(minDistance, "")) && (!Objects.equals(maxDistance, ""))) {
                pst = con.prepareStatement(SQL_GET_INFO_TABLE_WITH_MAX_DISTANCE);
                pst.setString(1, name);
                pst.setInt(2, Integer.parseInt(maxDistance));
            }
            if((!Objects.equals(minDistance, "")) && (Objects.equals(maxDistance, ""))) {
                pst = con.prepareStatement(SQL_GET_INFO_TABLE_WITH_MIN_DISTANCE);
                pst.setString(1, name);
                pst.setInt(2, Integer.parseInt(minDistance));
            }
            if((!Objects.equals(minDistance, "")) && (!Objects.equals(maxDistance, "")))  {
                pst = con.prepareStatement(SQL_GET_INFO_TABLE_AND_DISTANCE);
                pst.setString(1, name);
                pst.setInt(2, Integer.parseInt(minDistance));
                pst.setInt(3, Integer.parseInt(maxDistance));
            }

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    InfoTable it = new InfoTable();
                    it.setCityTo(rs.getString("city_to_name"));
                    int distance = rs.getInt("way_length");
                    it.setDistance(distance);
                    it.setPrice(distance*6/10);
                    table.add(it);
                }
            }
            if((!Objects.equals(minPrice, "")) && (!Objects.equals(maxPrice, "")))
                table = table.stream().filter((x)->((x.getPrice()>Integer.parseInt(minPrice)) && (x.getPrice()<Integer.parseInt(maxPrice)))).collect(Collectors.toList());
            if((Objects.equals(minPrice, "")) && (!Objects.equals(maxPrice, "")))
                table = table.stream().filter((x)->(x.getPrice()<Integer.parseInt(maxPrice))).collect(Collectors.toList());
            if((!Objects.equals(minPrice, "")) && (Objects.equals(maxPrice, "")))
                table = table.stream().filter((x)->(x.getPrice()>Integer.parseInt(minPrice))).collect(Collectors.toList());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                pst.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return table;
    }
    public static List<InfoTable> getInfoTable(String cityFrom, String cityTo) throws IOException, ParseException {
        List<InfoTable> infoTable = new ArrayList<>();
        List<Distance> distances = GoogleMaps.getDistance(cityFrom,cityTo);
        int index = 0;
        for (Distance distance :
                distances) {
            infoTable.add(new InfoTable(index, distance.getCityFrom(), distance.getCityTo(), distance.getDistance(), Calculate.avgPrice(distance.getDistance())));
            index++;
        }
        return infoTable;
    }

}
