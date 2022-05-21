package com.example.demos.web.controller;

import com.example.demos.model.OrderDao;
import com.example.demos.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PayServlet", value = "/pay")
public class PayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Integer orderId = Integer.parseInt(request.getParameter("id"));
        final Integer value = Integer.parseInt(request.getParameter("value"));
        final Integer money = Integer.parseInt(request.getParameter("money"));
        User user = (User) request.getSession().getAttribute("user");
        user.setMoney(money-value);
        OrderDao.changePayStatus(orderId,value,money);
        request.getSession().setAttribute("money",money-value);
        request.getSession().removeAttribute("orders");
        response.sendRedirect("user/order.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
