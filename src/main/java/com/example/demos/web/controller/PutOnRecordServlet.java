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

@WebServlet(name = "PutOnRecordServlet", value = "/putOnRecord")
public class PutOnRecordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        OrderDao.putOnRecord(id);
        if(UserDao.getUserNotify(OrderDao.getUserId(id))){
            String[] message = CreateMessage.putOnRecord(id);
            try {
                SendEmail.send(UserDao.getUserEmail(OrderDao.getUserId(id)),message[0],message[1]);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        response.sendRedirect("/employee/acceptOrder.jsp");
    }
}
