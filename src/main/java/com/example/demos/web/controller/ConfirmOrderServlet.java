package com.example.demos.web.controller;

import com.example.demos.model.utils.CreateMessage;
import com.example.demos.model.dao.OrderDao;
import com.example.demos.model.utils.SendEmail;
import com.example.demos.model.dao.UserDao;

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
        OrderDao.changeOrderStatus(orderId);
        if (UserDao.getUserNotify(OrderDao.getUserId(orderId))) {
            String email = UserDao.getUserEmail(OrderDao.getOrder(orderId).getUserLogin());
            try {
                SendEmail.send(email, CreateMessage.messageChangePaymentStatus(OrderDao.getOrder(orderId).getPrice()));
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        request.setAttribute("id", orderId);
        request.getSession().removeAttribute("orderList");
        request.getRequestDispatcher("/info").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
