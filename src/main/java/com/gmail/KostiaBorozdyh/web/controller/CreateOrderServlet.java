package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.dto.InfoTableDTO;
import com.gmail.KostiaBorozdyh.model.dto.OrderDTO;
import com.gmail.KostiaBorozdyh.model.entity.Order;
import com.gmail.KostiaBorozdyh.model.entity.User;
import com.gmail.KostiaBorozdyh.model.service.OrderService;
import com.gmail.KostiaBorozdyh.model.service.UserService;
import com.gmail.KostiaBorozdyh.model.utils.Convert;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateOrderServlet", value = "/createOrder")
public class CreateOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        OrderDTO orderDTO = (OrderDTO) session.getAttribute("newOrder");

        orderDTO.setUserId(user.getId());

        OrderService.save(Convert.toOrderFromOrderDTO(orderDTO));
        UserService.sendEmailAfterCreateOrder(user,orderDTO);

        session.removeAttribute("newOrder");
        session.removeAttribute("btn");
        response.sendRedirect("/user/order.jsp");
    }
}
