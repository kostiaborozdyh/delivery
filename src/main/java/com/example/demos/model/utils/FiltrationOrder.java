package com.example.demos.model.utils;

import com.example.demos.model.entity.InfoTable;
import com.example.demos.model.entity.Order;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class FiltrationOrder {

    public static List<Order> price(String minPrice, String maxPrice, List<Order> order) {

        if ((!Objects.equals(minPrice, "")) && (!Objects.equals(maxPrice, "")))
            order = order.stream().filter((x) -> ((x.getPrice() >= Integer.parseInt(minPrice)) && (x.getPrice() <= Integer.parseInt(maxPrice)))).collect(Collectors.toList());
        if ((Objects.equals(minPrice, "")) && (!Objects.equals(maxPrice, "")))
            order = order.stream().filter((x) -> (x.getPrice() <= Integer.parseInt(maxPrice))).collect(Collectors.toList());
        if ((!Objects.equals(minPrice, "")) && (Objects.equals(maxPrice, "")))
            order = order.stream().filter((x) -> (x.getPrice() >= Integer.parseInt(minPrice))).collect(Collectors.toList());
        return order;
    }

    public static List<Order> paymentStatus(String[] paymentStatus, List<Order> order) {

        if (paymentStatus != null)
            order = order.stream().filter((x) -> (Arrays.asList(paymentStatus).contains(x.getPaymentStatus()))).collect(Collectors.toList());

        return order;
    }

    public static List<Order> location(String[] location, List<Order> order) {

        if (location != null)
            order = order.stream().filter((x) -> (Arrays.asList(location).contains(x.getLocationStatus()))).collect(Collectors.toList());

        return order;
    }

    public static List<Order> dateCreate(String minDateCreate, String maxDateCreate, List<Order> order) {

        if ((!Objects.equals(minDateCreate, "")) && (!Objects.equals(maxDateCreate, "")))
            order = order.stream().filter((x) -> (x.getDateCreate().equals(LocalDate.parse(minDateCreate)) || x.getDateCreate().equals(LocalDate.parse(maxDateCreate)) || ((x.getDateCreate().isAfter(LocalDate.parse(minDateCreate))) && (x.getDateCreate().isBefore(LocalDate.parse(maxDateCreate)))))).collect(Collectors.toList());
        if ((Objects.equals(minDateCreate, "")) && (!Objects.equals(maxDateCreate, "")))
            order = order.stream().filter((x) -> (x.getDateCreate().isBefore(LocalDate.parse(maxDateCreate)) || x.getDateCreate().equals(LocalDate.parse(maxDateCreate)))).collect(Collectors.toList());
        if ((!Objects.equals(minDateCreate, "")) && (Objects.equals(maxDateCreate, "")))
            order = order.stream().filter((x) -> (x.getDateCreate().isAfter(LocalDate.parse(minDateCreate)) || x.getDateCreate().equals(LocalDate.parse(minDateCreate)))).collect(Collectors.toList());
        return order;
    }

    public static List<Order> dateOfArrival(String minDateOfArrival, String maxDateOfArrival, List<Order> order) {

        if ((!Objects.equals(minDateOfArrival, "")) && (!Objects.equals(maxDateOfArrival, "")))
            order = order.stream().filter((x) -> (x.getDateOfArrival().equals(LocalDate.parse(minDateOfArrival)) || x.getDateOfArrival().equals(LocalDate.parse(maxDateOfArrival)) || ((x.getDateOfArrival().isAfter(LocalDate.parse(minDateOfArrival))) && (x.getDateOfArrival().isBefore(LocalDate.parse(maxDateOfArrival)))))).collect(Collectors.toList());
        if ((Objects.equals(minDateOfArrival, "")) && (!Objects.equals(maxDateOfArrival, "")))
            order = order.stream().filter((x) -> (x.getDateOfArrival().isBefore(LocalDate.parse(maxDateOfArrival)) || x.getDateOfArrival().equals(LocalDate.parse(maxDateOfArrival)))).collect(Collectors.toList());
        if ((!Objects.equals(minDateOfArrival, "")) && (Objects.equals(maxDateOfArrival, "")))
            order = order.stream().filter((x) -> (x.getDateOfArrival().isAfter(LocalDate.parse(minDateOfArrival)) || x.getDateOfArrival().equals(LocalDate.parse(minDateOfArrival)))).collect(Collectors.toList());
        return order;
    }

    public static List<Order> cityFrom(Set<String> cityFrom, List<Order> order) {

        if (cityFrom != null)
            order = order.stream().filter((x) -> (cityFrom).contains(x.getCityFrom())).collect(Collectors.toList());

        return order;
    }

    public static List<Order> cityTo(Set<String> cityTo, List<Order> order) {

        if (cityTo != null)
            order = order.stream().filter((x) -> (cityTo.contains(x.getCityTo()))).collect(Collectors.toList());

        return order;
    }

    public static List<Order> sorting(String sort, List<Order> order) {
        if (sort.equals("sortByMinPrice"))
            order = order.stream().sorted(Comparator.comparingInt(Order::getPrice)).collect(Collectors.toList());
        if (sort.equals("sortByMaxPrice"))
            order = order.stream().sorted((x, y) -> (y.getPrice() - x.getPrice())).collect(Collectors.toList());
        if (sort.equals("sortByMinDateCreate"))
            order = order.stream().sorted(Comparator.comparing(Order::getDateCreate)).collect(Collectors.toList());
        if (sort.equals("sortByMaxDateCreate"))
            order = order.stream().sorted((x, y) -> (y.getDateCreate().compareTo(x.getDateCreate()))).collect(Collectors.toList());
        return order;
    }

    public static List<InfoTable> sortingTable(String sort, List<InfoTable> infoTable) {
        if (sort.equals("sortByMinPriceTable"))
            infoTable = infoTable.stream().sorted(Comparator.comparingInt(InfoTable::getPrice)).collect(Collectors.toList());
        if (sort.equals("sortByMaxPriceTable"))
            infoTable = infoTable.stream().sorted((x, y) -> (y.getPrice() - x.getPrice())).collect(Collectors.toList());
        if (sort.equals("sortByMinCityFromTable"))
            infoTable = infoTable.stream().sorted(Comparator.comparing(InfoTable::getCityFrom)).collect(Collectors.toList());
        if (sort.equals("sortByMaxCityFromTable"))
            infoTable = infoTable.stream().sorted((x, y) -> (y.getCityFrom().compareTo(x.getCityFrom()))).collect(Collectors.toList());
        if (sort.equals("sortByMinCityToTable"))
            infoTable = infoTable.stream().sorted(Comparator.comparing(InfoTable::getCityTo)).collect(Collectors.toList());
        if (sort.equals("sortByMaxCityToTable"))
            infoTable = infoTable.stream().sorted((x, y) -> (y.getCityTo().compareTo(x.getCityTo()))).collect(Collectors.toList());
        return infoTable;
    }

}
