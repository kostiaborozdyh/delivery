package com.example.demos.web.controller;

import com.example.demos.model.CreateMessage;
import com.example.demos.model.OrderDao;
import com.example.demos.model.SendEmail;
import com.example.demos.model.entity.User;

import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SendEmailOrderServlet", value = "/sendEmailOrder")
public class SendEmailOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("user")!=null) {
            Integer id = Integer.valueOf(request.getParameter("idOrder"));
            String[] email = CreateMessage.messageSendOrder(OrderDao.getOrder(id));
            User user = (User) request.getSession().getAttribute("user");
            try {
                SendEmail.send(user.getEmail(), email[0], email[1]);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("/user/order.jsp");
        } else  response.sendRedirect("/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
