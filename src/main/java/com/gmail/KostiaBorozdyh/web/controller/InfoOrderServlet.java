package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Controller for Getting info about Order
 */
@WebServlet(name = "InfoOrderServlet", value = "/info")
public class InfoOrderServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Integer orderId = Integer.parseInt(request.getParameter("id"));
        request.getSession().setAttribute("infoOrder", OrderService.getOrderById(orderId));
        if (request.getSession().getAttribute("role").equals("user")) {
            response.sendRedirect("/user/userOrder.jsp");
        } else {
            response.sendRedirect("/man/orderUser.jsp");
        }
    }
}
