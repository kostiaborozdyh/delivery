package com.gmail.KostiaBorozdyh.model.utils;


import com.gmail.KostiaBorozdyh.model.dao.OrderDao;
import com.gmail.KostiaBorozdyh.model.entity.Order;
import com.gmail.KostiaBorozdyh.model.dto.PointDTO;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Calculate utils
 */
public class Calculate {
    private Calculate() {

    }

    /**
     * Calculate average price delivery by distance
     *
     * @param distance Integer distance
     * @return price delivery
     */
    public static Integer avgPrice(Integer distance) {
        if (distance == 0) {
            return 100;
        }
        return distance * 15 / 10;
    }

    /**
     * Calculate price delivery by distance, volume and weight
     *
     * @param distance Integer distance
     * @param volume   Integer  parcel volume
     * @param weight   Integer parcel weight
     * @return price delivery
     */
    public static Integer deliveryPrice(Integer distance, Integer volume, String weight) {
        return Math.max(avgPrice(distance) * Integer.parseInt(weight) * volume / 10, 100);
    }

    /**
     * Calculate parcel volume by parcel height, length, width
     *
     * @param height String parcel height
     * @param length String  parcel length
     * @param width  String parcel width
     * @return parcel volume
     */
    public static Integer volume(String height, String length, String width) {
        return Integer.parseInt(height) * Integer.parseInt(length) * Integer.parseInt(width);
    }

    /**
     * Calculate arrival time delivery by distance
     *
     * @param distance Integer distance
     * @return arrival time delivery in LocalDate format
     */
    public static LocalDate arrivalTime(Integer distance) {
        distance = distance / 400 + 1;
        return LocalDate.now().plusDays(distance);
    }

    /**
     * Calculate new arrival time delivery by dateOfCreate and dateOfArrival order
     *
     * @param dateOfCreate  LocalDate date of create order
     * @param dateOfArrival LocalDate date of arrival order
     * @return new arrival time delivery in LocalDate format
     */
    public static LocalDate newArrivalTime(LocalDate dateOfCreate, LocalDate dateOfArrival) {
        return LocalDate.now().plusDays(diffDays(dateOfCreate, dateOfArrival));
    }

    /**
     * Calculate the difference in days between two dates
     *
     * @param dateOfSending LocalDate date of sending order
     * @param dateOfArrival LocalDate date of arrival order
     * @return difference in days
     */
    public static int diffDays(LocalDate dateOfSending, LocalDate dateOfArrival) {
        int length1 = dateOfSending.getDayOfYear();
        int length2 = dateOfArrival.getDayOfYear();
        if (dateOfArrival.getYear() != dateOfSending.getYear()) {
            return length2 - length1 + 365;
        } else {
            return length2 - length1;
        }
    }

    /**
     * Convert orderList to String cityFrom Set
     *
     * @param orderList orders List
     * @return Set cityFrom. If any problems returns empty Set.
     */
    public static Set<String> cityFromSet(List<Order> orderList) {
        return Convert.toCitySetFromOrderList(orderList, true);
    }

    /**
     * Convert orderList to String cityTo Set
     *
     * @param orderList orders List
     * @return Set cityTo. If any present problems empty Set.
     */
    public static Set<String> cityToSet(List<Order> orderList) {
        return Convert.toCitySetFromOrderList(orderList, false);
    }

    /**
     * Calculate pagination List by inputList
     *
     * @param inputList Input List
     * @return List of Integer(list of pages number). If any problems returns empty list.
     */
    public static <T> List<Integer> getPaginationList(List<T> inputList) {
        return getPaginationList(inputList.size());
    }

    /**
     * Calculate pagination List by number of elements
     *
     * @param count Integer number of elements
     * @return List of Integer(list of pages number). If any problems returns empty list.
     */
    public static List<Integer> getPaginationList(Integer count) {
        List<Integer> list = new ArrayList<>();
        int a = 0;
        if (count % 5 != 0) a = 1;
        int length = count / 5 + a;
        for (int i = 0; i < length; i++) {
            list.add(i + 1);
        }
        if (count <= 5) return null;
        else return list;
    }

    /**
     * Return five elements from input list with offset index*5
     *
     * @param inputList Input List
     * @param index     Integer number of page
     * @return List of element. If any problems returns empty list.
     */
    public static <T> List<T> getFiveElements(List<T> inputList, int index) {
        List<T> list = new ArrayList<>();
        int lastIndex = index * 5;
        if (inputList.size() < index * 5) {
            lastIndex = inputList.size() % ((index - 1) * 5) + ((index - 1) * 5);
        }
        for (int i = (index - 1) * 5; i < lastIndex; i++) {
            list.add(inputList.get(i));
        }
        return list;
    }

    /**
     * Check if the page number is outside the list
     *
     * @param id Index page Number
     * @param list     Input list
     * @return page number. If any problems returns id.
     */
    public static <T> int pageId(int id, List<T> list) {
        if (id == 0) {
            id = 1;
        }
        if (id == list.size() + 1) {
            id = list.size();
        }
        return id;
    }

    /**
     * Return PointDTO at the moment by Order identifier
     *
     * @param id Order identifier
     * @return PointDTO. If any problems returns null.
     */
    public static PointDTO getPointAtTheMoment(Integer id) {
        Order order = OrderDao.getOrder(id);
        PointDTO cityFromPoint = GoogleMaps.getCityCoordinates(order.getCityFrom());
        PointDTO cityToPoint = GoogleMaps.getCityCoordinates(order.getCityTo());
        int days = diffDays(order.getDateOfSending(), order.getDateOfArrival());
        int time = LocalDateTime.now().getHour();
        if (order.getDateOfSending().equals(LocalDate.now()) && time < 22) {
            return cityFromPoint;
        }
        if ((order.getDateOfArrival().equals(LocalDate.now()) && time > 15) || (order.getDateOfArrival().isBefore(LocalDate.now()))) {
            return cityToPoint;
        }
        int diffDays = diffDays(order.getDateOfSending(), LocalDate.now());
        if (diffDays == 0) time = time - 22;
        else time = time + (diffDays - 1) * 24 + 2;
        return currentPoint(cityFromPoint, cityToPoint, days, time);
    }

    /**
     * Calculate PointDTO between cityFromPoint and cityToPoint  at the moment
     *
     * @param cityFromPoint PointDTO cityFromPoint
     * @param cityToPoint PointDTO cityFromPoint
     * @param days Integer parcel delivery time in days
     * @param time Integer current time in hours
     * @return PointDTO. If any problems returns null.
     */
    public static PointDTO currentPoint(PointDTO cityFromPoint, PointDTO cityToPoint, int days, int time) {
        double latCityFrom = Double.parseDouble(cityFromPoint.getLatitude());
        double lngCityFrom = Double.parseDouble(cityFromPoint.getLongitude());
        double latCityTo = Double.parseDouble(cityToPoint.getLatitude());
        double lngCityTo = Double.parseDouble(cityToPoint.getLongitude());
        double diffLat = (latCityFrom - latCityTo) / (days * 24 - 7);
        double diffLng = (lngCityFrom - lngCityTo) / (days * 24 - 7);
        String lat = String.valueOf(latCityFrom - diffLat * time);
        String lng = String.valueOf(lngCityFrom - diffLng * time);
        return new PointDTO(lat, lng);
    }

}
