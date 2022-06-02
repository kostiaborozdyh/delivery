package com.example.demos.model.utils;

import com.example.demos.model.entity.InfoTable;
import com.example.demos.model.entity.Order;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class FiltrationOrder {


    public static List<Order> price(String minPrice, String maxPrice, List<Order> order) {
        Integer minimalPrice = null;
        Integer maximalPrice = null;

        if (!Objects.equals(minPrice, "")) {
            minimalPrice = Integer.parseInt(minPrice);
        }
        if (!Objects.equals(maxPrice, "")) {
            maximalPrice = Integer.parseInt(maxPrice);
        }

        Integer finalMinimalPrice = minimalPrice;
        Integer finalMaximalPrice = maximalPrice;

        if (minimalPrice != null && maximalPrice != null) {

            order = order.stream()
                    .filter(x -> (x.getPrice() >= finalMinimalPrice && x.getPrice() <= finalMaximalPrice))
                    .collect(Collectors.toList());
        }

        if (minimalPrice == null && maximalPrice != null) {
            order = order.stream()
                    .filter(x -> (x.getPrice() <= finalMaximalPrice))
                    .collect(Collectors.toList());
        }

        if (minimalPrice != null && maximalPrice == null) {
            order = order.stream()
                    .filter(x -> (x.getPrice() >= finalMinimalPrice))
                    .collect(Collectors.toList());
        }

        return order;
    }

    public static List<Order> paymentStatus(String[] paymentStatus, List<Order> order) {

        if (paymentStatus != null) {
            order = order.stream()
                    .filter(x -> (Arrays.asList(paymentStatus).contains(x.getPaymentStatus())))
                    .collect(Collectors.toList());
        }
        return order;
    }

    public static List<Order> location(String[] location, List<Order> order) {

        if (location != null) {
            order = order.stream()
                    .filter(x -> (Arrays.asList(location).contains(x.getLocationStatus())))
                    .collect(Collectors.toList());
        }
        return order;
    }

    public static List<Order> dateCreate(String minDateCreate, String maxDateCreate, List<Order> order) {
        LocalDate minimalDateCreate = null;
        LocalDate maximalDateCreate = null;

        if (!Objects.equals(minDateCreate, "")) {
            minimalDateCreate = LocalDate.parse(minDateCreate).minusDays(1);
        }
        if (!Objects.equals(maxDateCreate, "")) {
            maximalDateCreate = LocalDate.parse(maxDateCreate).plusDays(1);
        }

        LocalDate finalMaximalDateCreate = maximalDateCreate;
        LocalDate finalMinimalDateCreate = minimalDateCreate;

        if ((minimalDateCreate != null) && (maximalDateCreate != null)) {
            order = order.stream()
                    .filter(x -> (x.getDateCreate().isBefore(finalMaximalDateCreate) && x.getDateCreate().isAfter(finalMinimalDateCreate)))
                    .collect(Collectors.toList());
        }

        if ((minimalDateCreate == null) && (maximalDateCreate != null)) {
            order = order.stream()
                    .filter(x -> (x.getDateCreate().isBefore(finalMaximalDateCreate)))
                    .collect(Collectors.toList());
        }

        if ((minimalDateCreate != null) && (maximalDateCreate == null)) {
            order = order.stream()
                    .filter(x -> (x.getDateCreate().isAfter(finalMinimalDateCreate)))
                    .collect(Collectors.toList());
        }

        return order;
    }


    public static List<Order> dateOfArrival(String minDateOfArrival, String maxDateOfArrival, List<Order> order) {
        LocalDate minimalDateOfArrival = null;
        LocalDate maximalDateOfArrival = null;

        if (!Objects.equals(minDateOfArrival, "")) {
            minimalDateOfArrival = LocalDate.parse(minDateOfArrival).minusDays(1);
        }
        if (!Objects.equals(maxDateOfArrival, "")) {
            maximalDateOfArrival = LocalDate.parse(maxDateOfArrival).plusDays(1);
        }

        LocalDate finalMaximalDateOfArrival = maximalDateOfArrival;
        LocalDate finalMinimalDateOfArrival = minimalDateOfArrival;

        if ((minimalDateOfArrival != null) && (maximalDateOfArrival != null)) {
            order = order.stream()
                    .filter(x -> (x.getDateOfArrival().isBefore(finalMaximalDateOfArrival) && x.getDateOfArrival().isAfter(finalMinimalDateOfArrival)))
                    .collect(Collectors.toList());
        }

        if ((minimalDateOfArrival == null) && (maximalDateOfArrival != null)) {
            order = order.stream()
                    .filter(x -> (x.getDateOfArrival().isBefore(finalMaximalDateOfArrival)))
                    .collect(Collectors.toList());
        }

        if ((minimalDateOfArrival != null) && (maximalDateOfArrival == null)) {
            order = order.stream()
                    .filter(x -> (x.getDateOfArrival().isAfter(finalMinimalDateOfArrival)))
                    .collect(Collectors.toList());
        }

        return order;
    }

    public static List<Order> cityFrom(Set<String> cityFrom, List<Order> order) {

        if (cityFrom != null) {
            order = order.stream()
                    .filter(x -> (cityFrom).contains(x.getCityFrom()))
                    .collect(Collectors.toList());
        }

        return order;
    }

    public static List<Order> cityTo(Set<String> cityTo, List<Order> order) {

        if (cityTo != null) {
            order = order.stream()
                    .filter(x -> (cityTo.contains(x.getCityTo())))
                    .collect(Collectors.toList());
        }
        return order;
    }

    public static List<Order> sorting(String sort, List<Order> order) {

        switch (sort) {
            case "sortByMinPrice": {
                order = order.stream()
                        .sorted(Comparator.comparingInt(Order::getPrice))
                        .collect(Collectors.toList());
            }
            break;
            case "sortByMaxPrice": {
                order = order.stream()
                        .sorted((x, y) -> (y.getPrice() - x.getPrice()))
                        .collect(Collectors.toList());
            }
            break;
            case "sortByMinDateCreate": {
                order = order.stream()
                        .sorted(Comparator.comparing(Order::getDateCreate))
                        .collect(Collectors.toList());
            }
            break;
            case "sortByMaxDateCreate": {
                order = order.stream()
                        .sorted((x, y) -> (y.getDateCreate().compareTo(x.getDateCreate())))
                        .collect(Collectors.toList());
            }
            break;

        }
        return order;
    }

    public static List<InfoTable> sortingTable(String sort, List<InfoTable> infoTable) {
        switch (sort) {
            case "sortByMinPriceTable": {
                infoTable = infoTable.stream()
                        .sorted(Comparator.comparingInt(InfoTable::getPrice))
                        .collect(Collectors.toList());
            }
            break;
            case "sortByMaxPriceTable": {
                infoTable = infoTable.stream()
                        .sorted((x, y) -> (y.getPrice() - x.getPrice()))
                        .collect(Collectors.toList());
            }
            break;
            case "sortByMinCityFromTable": {
                infoTable = infoTable.stream()
                        .sorted(Comparator.comparing(InfoTable::getCityFrom))
                        .collect(Collectors.toList());
            }
            break;
            case "sortByMaxCityFromTable": {
                infoTable = infoTable.stream()
                        .sorted((x, y) -> (y.getCityFrom().compareTo(x.getCityFrom())))
                        .collect(Collectors.toList());
            }
            break;
            case "sortByMinCityToTable": {
                infoTable = infoTable.stream()
                        .sorted(Comparator.comparing(InfoTable::getCityTo))
                        .collect(Collectors.toList());
            }
            break;
            case "sortByMaxCityToTable": {
                infoTable = infoTable.stream()
                        .sorted((x, y) -> (y.getCityTo().compareTo(x.getCityTo())))
                        .collect(Collectors.toList());
            }
            break;

        }
        return infoTable;
    }

}
