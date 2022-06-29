package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.dto.InfoTableDTO;
import com.gmail.KostiaBorozdyh.model.dto.OrderDTO;
import com.gmail.KostiaBorozdyh.model.service.InfoTableService;
import com.gmail.KostiaBorozdyh.model.utils.Calculate;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * Controller for Calculating price for user order
 */
@WebServlet("/calculateBag")
public class CalculateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/calculate.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        final String cityFrom = request.getParameter("cityFrom");
        final String cityTo = request.getParameter("cityTo");
        final String weight = request.getParameter("weight");
        final String height = request.getParameter("height");
        final String length = request.getParameter("length");
        final String width = request.getParameter("width");
        final String address = request.getParameter("address");
        final String info = request.getParameter("info");
        int price, volume;
        List<InfoTableDTO> distanceList = InfoTableService.getInfoTable(cityFrom, cityTo);

        volume = Calculate.volume(height, length, width);
        price = Calculate.deliveryPrice(distanceList.get(0).getDistance(), volume, weight);

        OrderDTO orderDTO = new OrderDTO.Builder()
                .description(info)
                .address(address)
                .weight(Integer.parseInt(weight))
                .height(Integer.parseInt(height))
                .length(Integer.parseInt(length))
                .width(Integer.parseInt(width))
                .cityFrom(distanceList.get(0).getCityFrom())
                .cityTo(distanceList.get(0).getCityTo())
                .price(price)
                .volume(volume)
                .distance(distanceList.get(0).getDistance())
                .build();

        if (address != null) {
            session.setAttribute("newOrder", orderDTO);
            session.setAttribute("btn", "unblock");
            response.sendRedirect("/user/createOrder.jsp");
        } else {
            session.setAttribute("calculateTable", orderDTO);
            response.sendRedirect("/calculate.jsp");
        }
    }
}
