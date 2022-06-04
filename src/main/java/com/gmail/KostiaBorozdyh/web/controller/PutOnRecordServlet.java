package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.utils.CreateMessage;
import com.gmail.KostiaBorozdyh.model.dao.OrderDao;
import com.gmail.KostiaBorozdyh.model.utils.SendEmail;
import com.gmail.KostiaBorozdyh.model.dao.UserDao;

import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PutOnRecordServlet", value = "/putOnRecord")
public class PutOnRecordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        OrderDao.putOnRecord(id);
        if (UserDao.getUserNotify(OrderDao.getUserId(id))) {
            try {
                SendEmail.send(UserDao.getUserEmail(OrderDao.getUserId(id)), CreateMessage.putOnRecord(id));
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        response.sendRedirect("/employee/acceptOrder.jsp");
    }
}
