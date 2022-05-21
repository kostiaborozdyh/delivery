package com.example.demos.web.controller;

import com.example.demos.model.CreateMessage;
import com.example.demos.model.OrderDao;
import com.example.demos.model.SendEmail;
import com.example.demos.model.UserDao;

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
           String[] message = CreateMessage.messageChangePaymentStatus(OrderDao.getOrder(orderId).getPrice());
           try {
               SendEmail.send(email,message[0],message[1]);
           } catch (MessagingException e) {
               throw new RuntimeException(e);
           }
       }
        request.setAttribute("id",orderId);
        request.getSession().removeAttribute("orderList");
        request.getRequestDispatcher("/info").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
