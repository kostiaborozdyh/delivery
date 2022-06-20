package com.gmail.KostiaBorozdyh.model.utils;

import com.gmail.KostiaBorozdyh.model.dto.OrderDTO;
import com.gmail.KostiaBorozdyh.model.entity.Order;

public class Convert {

    public static Order toOrderFromOrderDTO(OrderDTO orderDTO){
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
}
