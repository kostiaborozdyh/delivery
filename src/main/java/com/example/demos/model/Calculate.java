package com.example.demos.model;


import com.example.demos.model.entity.InfoTable;
import com.example.demos.model.entity.Order;
import com.example.demos.model.entity.Point;
import org.json.simple.parser.ParseException;


import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Calculate {
    public static Integer avgPrice(Integer distance) {
        if (distance == 0) return 100;
        return distance * 15 / 10;
    }

    public static Integer deliveryPrice(Integer distance, Integer volume, String weight) {
        return Math.max(avgPrice(distance) * Integer.parseInt(weight) * volume / 10, 100);
    }

    public static Integer volume(String height, String length, String width) {
        return Integer.parseInt(height) * Integer.parseInt(length) * Integer.parseInt(width);
    }

    public static LocalDate arrivalTime(Integer distance) {
        distance = distance / 400 + 1;
        return LocalDate.now().plusDays(distance);
    }

    public static LocalDate newArrivalTime(LocalDate dateOfCreate, LocalDate dateOfArrival) {
        return LocalDate.now().plusDays(days(dateOfCreate, dateOfArrival));
    }

    public static int days(LocalDate dateOfSending, LocalDate dateOfArrival) {
        int length1 = dateOfSending.getDayOfYear();
        int length2 = dateOfArrival.getDayOfYear();
        if (dateOfArrival.getYear() != dateOfSending.getYear()) {
            return length2 - length1 + 365;
        } else {
            return length2 - length1;
        }
    }

    public static Point getPointAtTheMoment(Integer id) throws IOException, ParseException {
        Order order = OrderDao.getOrder(id);
        Point cityFromPoint = GoogleMaps.getCityCoordinates(order.getCityFrom());
        Point cityToPoint = GoogleMaps.getCityCoordinates(order.getCityTo());
        int days = days(order.getDateOfSending(), order.getDateOfArrival());
        int time = LocalDateTime.now().getHour();
        if (order.getDateOfSending().equals(LocalDate.now()) && time < 22) return cityFromPoint;
        if ((order.getDateOfArrival().equals(LocalDate.now()) && time > 15) || (order.getDateOfArrival().isBefore(LocalDate.now())))
            return cityToPoint;
        int diffDays = days(order.getDateOfSending(), LocalDate.now());
        if (diffDays == 0) time = time - 22;
        else time = time + (diffDays - 1) * 24 + 2;
        return currentPoint(cityFromPoint, cityToPoint, days, time);
    }

    private static Point currentPoint(Point cityFromPoint, Point cityToPoint, int days, int time) {
        double latCityFrom = Double.parseDouble(cityFromPoint.getLatitude());
        double lngCityFrom = Double.parseDouble(cityFromPoint.getLongitude());
        double latCityTo = Double.parseDouble(cityToPoint.getLatitude());
        double lngCityTo = Double.parseDouble(cityToPoint.getLongitude());
        double diffLat = (latCityFrom - latCityTo) / (days * 24 - 7);
        double diffLng = (lngCityFrom - lngCityTo) / (days * 24 - 7);
        String lat = String.valueOf(latCityFrom - diffLat * time);
        String lng = String.valueOf(lngCityFrom - diffLng * time);
        return new Point(lat, lng);
    }

    public static List<Integer> getPaginationList(List<InfoTable> infoTable) {
        List<Integer> list = new ArrayList<>();
        int size = infoTable.size();
        int a = 0;
        if (size % 5 != 0) a = 1;
        int length = size / 5 + a;
        for (int i = 0; i < length; i++) {
            list.add(i + 1);
        }
        if (size <= 5) return null;
        else return list;
    }

    public static List<InfoTable> getFiveElements(List<InfoTable> infoTable, int index) {
        List<InfoTable> list = new ArrayList<>();
        int lastIndex = index * 5;
        if (infoTable.size() < index * 5) {
            lastIndex = infoTable.size() % ((index - 1)*5)+((index - 1)*5);
        }
        for (int i = (index - 1) * 5; i < lastIndex; i++) {
            list.add(infoTable.get(i));
        }
        return list;
    }

}
