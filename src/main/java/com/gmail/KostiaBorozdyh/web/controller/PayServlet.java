package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.entity.User;
import com.gmail.KostiaBorozdyh.model.service.OrderService;
import com.gmail.KostiaBorozdyh.model.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Controller for Paying Order
 */
@WebServlet(name = "PayServlet", value = "/pay")
public class PayServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Integer orderId = Integer.parseInt(request.getParameter("id"));
        final Integer value = Integer.parseInt(request.getParameter("value"));
        final Integer money = Integer.parseInt(request.getParameter("money"));

        User user = (User) request.getSession().getAttribute("user");
        user.setMoney(money - value);

        OrderService.changeOrderPaymentStatusAfterUserPay(orderId);
        UserService.changeMoney(user);
        request.getSession().setAttribute("money", user.getMoney());

        response.sendRedirect("/resetOrder");
    }
}
