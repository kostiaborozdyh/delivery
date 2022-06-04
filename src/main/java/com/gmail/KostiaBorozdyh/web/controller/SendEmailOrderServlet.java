package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.utils.CreateMessage;
import com.gmail.KostiaBorozdyh.model.dao.OrderDao;
import com.gmail.KostiaBorozdyh.model.utils.SendEmail;
import com.gmail.KostiaBorozdyh.model.entity.User;

import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SendEmailOrderServlet", value = "/sendEmailOrder")
public class SendEmailOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Integer id = Integer.valueOf(request.getParameter("idOrder"));
            User user = (User) request.getSession().getAttribute("user");

            try {
                SendEmail.send(user.getEmail(), CreateMessage.messageSendOrder(OrderDao.getOrder(id)));
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

            response.sendRedirect("/user/order.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}