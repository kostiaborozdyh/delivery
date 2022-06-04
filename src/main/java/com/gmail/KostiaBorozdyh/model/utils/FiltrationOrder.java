package com.gmail.KostiaBorozdyh.model.utils;

import com.gmail.KostiaBorozdyh.model.entity.FilterOrder;
import com.gmail.KostiaBorozdyh.model.entity.InfoTable;
import com.gmail.KostiaBorozdyh.model.entity.Order;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class FiltrationOrder {
    private static final Logger log = Logger.getLogger(FiltrationOrder.class);
    private static List<Order> orderList;

    public static List<Order> doFilter(List<Order> orders, FilterOrder filterOrder) {
        orderList = orders;

        priceFilter(filterOrder.getMinPrice(), filterOrder.getMaxPrice());
        paymentStatusFilter(filterOrder.getPaymentStatus());
        locationFilter(filterOrder.getLocation());
        dateCreateFilter(filterOrder.getMinDateCreate(), filterOrder.getMaxDateCreate());
        dateOfArrivalFilter(filterOrder.getMinDateOfArrival(), filterOrder.getMaxDateOfArrival());
        cityFromFilter(filterOrder.getCityFrom());
        cityToFilter(filterOrder.getCityTo());
        sorting(filterOrder.getSort());

        return orderList;
    }


    private static void priceFilter(String minPrice, String maxPrice) {
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

            orderList = orderList.stream()
                    .filter(x -> (x.getPrice() >= finalMinimalPrice && x.getPrice() <= finalMaximalPrice))
                    .collect(Collectors.toList());
        }

        if (minimalPrice == null && maximalPrice != null) {
            orderList = orderList.stream()
                    .filter(x -> (x.getPrice() <= finalMaximalPrice))
                    .collect(Collectors.toList());
        }

        if (minimalPrice != null && maximalPrice == null) {
            orderList = orderList.stream()
                    .filter(x -> (x.getPrice() >= finalMinimalPrice))
                    .collect(Collectors.toList());
        }

    }

    private static void paymentStatusFilter(String[] paymentStatus) {

        if (paymentStatus != null) {
            orderList = orderList.stream()
                    .filter(x -> (Arrays.asList(paymentStatus).contains(x.getPaymentStatus())))
                    .collect(Collectors.toList());
        }
    }

    private static void locationFilter(String[] location) {

        if (location != null) {
            orderList = orderList.stream()
                    .filter(x -> (Arrays.asList(location).contains(x.getLocationStatus())))
                    .collect(Collectors.toList());
        }
    }

    private static void dateCreateFilter(String minDateCreate, String maxDateCreate) {
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
            orderList = orderList.stream()
                    .filter(x -> (x.getDateCreate().isBefore(finalMaximalDateCreate) && x.getDateCreate().isAfter(finalMinimalDateCreate)))
                    .collect(Collectors.toList());
        }

        if ((minimalDateCreate == null) && (maximalDateCreate != null)) {
            orderList = orderList.stream()
                    .filter(x -> (x.getDateCreate().isBefore(finalMaximalDateCreate)))
                    .collect(Collectors.toList());
        }

        if ((minimalDateCreate != null) && (maximalDateCreate == null)) {
            orderList = orderList.stream()
                    .filter(x -> (x.getDateCreate().isAfter(finalMinimalDateCreate)))
                    .collect(Collectors.toList());
        }

    }


    private static void dateOfArrivalFilter(String minDateOfArrival, String maxDateOfArrival) {
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
            orderList = orderList.stream()
                    .filter(x -> (x.getDateOfArrival().isBefore(finalMaximalDateOfArrival) && x.getDateOfArrival().isAfter(finalMinimalDateOfArrival)))
                    .collect(Collectors.toList());
        }

        if ((minimalDateOfArrival == null) && (maximalDateOfArrival != null)) {
            orderList = orderList.stream()
                    .filter(x -> (x.getDateOfArrival().isBefore(finalMaximalDateOfArrival)))
                    .collect(Collectors.toList());
        }

        if ((minimalDateOfArrival != null) && (maximalDateOfArrival == null)) {
            orderList = orderList.stream()
                    .filter(x -> (x.getDateOfArrival().isAfter(finalMinimalDateOfArrival)))
                    .collect(Collectors.toList());
        }
    }

    private static void cityFromFilter(String[] cityFrom) {

        if (cityFrom != null) {
            orderList = orderList.stream()
                    .filter(x -> (Arrays.asList(cityFrom).contains(x.getCityFrom())))
                    .collect(Collectors.toList());
        }
    }

    private static void cityToFilter(String[] cityTo) {

        if (cityTo != null) {
            orderList = orderList.stream()
                    .filter(x -> (Arrays.asList(cityTo).contains(x.getCityTo())))
                    .collect(Collectors.toList());
        }
    }

    private static void sorting(String sort) {
        if(sort!=null) {
            switch (sort) {
                case "sortByMinPrice": {
                    orderList = orderList.stream()
                            .sorted(Comparator.comparingInt(Order::getPrice))
                            .collect(Collectors.toList());
                }
                break;
                case "sortByMaxPrice": {
                    orderList = orderList.stream()
                            .sorted((x, y) -> (y.getPrice() - x.getPrice()))
                            .collect(Collectors.toList());
                }
                break;
                case "sortByMinDateCreate": {
                    orderList = orderList.stream()
                            .sorted(Comparator.comparing(Order::getDateCreate))
                            .collect(Collectors.toList());
                }
                break;
                case "sortByMaxDateCreate": {
                    orderList = orderList.stream()
                            .sorted((x, y) -> (y.getDateCreate().compareTo(x.getDateCreate())))
                            .collect(Collectors.toList());
                }
                break;

            }
        }
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
