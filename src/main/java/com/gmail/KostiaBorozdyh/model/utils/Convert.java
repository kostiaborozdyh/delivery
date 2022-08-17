package com.gmail.KostiaBorozdyh.model.utils;

import com.gmail.KostiaBorozdyh.model.dto.OrderDTO;
import com.gmail.KostiaBorozdyh.model.entity.Order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Convert utils
 */
public class Convert {

    /**
     * Convert from orderDTO to order
     *
     * @param orderDTO OrderDTO
     * @return order. If any problems returns empty order.
     */
    public static Order toOrderFromOrderDTO(OrderDTO orderDTO) {
        Order order = new Order();
        order.setDescription(orderDTO.getDescription());
        order.setAddress(orderDTO.getAddress());
        order.setCityFrom(orderDTO.getCityFrom());
        order.setCityTo(orderDTO.getCityTo());
        order.setWeight(orderDTO.getWeight());
        order.setPrice(orderDTO.getPrice());
        order.setVolume(orderDTO.getVolume());
        order.setDistance(orderDTO.getDistance());
        order.setUserId(orderDTO.getUserId());
        return order;
    }

    /**
     * Convert from orderList to Set string by boolean cityFrom
     *
     * @param orderList orderList
     * @param cityFrom  boolean cityFrom
     * @return Set String. If any problems returns empty Set.
     */
    public static Set<String> toCitySetFromOrderList(List<Order> orderList, boolean cityFrom) {
        Set<String> stringSet = new HashSet<>();
        for (Order order :
                orderList) {
            if (cityFrom) {
                stringSet.add(order.getCityFrom());
            } else {
                stringSet.add(order.getCityTo());
            }
        }
        return stringSet;
    }
}
