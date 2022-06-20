package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.service.OrderService;
import com.gmail.KostiaBorozdyh.model.service.UserService;
import com.gmail.KostiaBorozdyh.model.utils.CreateMessage;
import com.gmail.KostiaBorozdyh.model.dao.OrderDao;
import com.gmail.KostiaBorozdyh.model.utils.SendEmail;
import com.gmail.KostiaBorozdyh.model.dao.UserDao;

import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ConfirmOrderServlet", value = "/confirmOrder")
public class ConfirmOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Integer orderId = Integer.parseInt(request.getParameter("id"));

        OrderService.changeOrderPaymentStatusAfterConfirmByManager(orderId);
        UserService.sendEmailByOrderId(orderId,1);

        request.setAttribute("id", orderId);
        request.getSession().removeAttribute("orderList");
        request.getRequestDispatcher("/info").forward(request, response);
    }
}
