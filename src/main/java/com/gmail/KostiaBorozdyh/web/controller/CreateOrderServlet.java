package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.entity.InfoTable;
import com.gmail.KostiaBorozdyh.model.entity.Order;
import com.gmail.KostiaBorozdyh.model.entity.User;
import com.gmail.KostiaBorozdyh.model.service.OrderService;
import com.gmail.KostiaBorozdyh.model.service.UserService;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateOrderServlet", value = "/createOrder")
public class CreateOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        InfoTable infoTable = (InfoTable) session.getAttribute("newOrder");
        final String info = (String) session.getAttribute("orderInfo");
        final String address = (String) session.getAttribute("orderAddress");

        User user = (User) session.getAttribute("user");

        OrderService.save(new Order(infoTable,info,address,user.getId()));
        UserService.sendEmailAfterCreateOrder(user,infoTable);

        session.removeAttribute("newOrder");
        session.removeAttribute("orderInfo");
        session.removeAttribute("orderAddress");
        session.removeAttribute("heightParcel");
        session.removeAttribute("lengthParcel");
        session.removeAttribute("widthParcel");
        session.removeAttribute("btn");
        response.sendRedirect("/user/order.jsp");
    }
}
