package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.dao.OrderDao;
import com.gmail.KostiaBorozdyh.model.dao.UserDao;
import com.gmail.KostiaBorozdyh.security.Security;

import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GiveOrderServlet", value = "/giveOrder")
public class GiveOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           Integer id = Integer.parseInt(request.getParameter("id"));
        try {
            String code = Security.sendCode(UserDao.getUserEmail(OrderDao.getUserId(id)));
            request.getSession().setAttribute("id",id);
            request.getSession().setAttribute("code",code);
            response.sendRedirect("/employee/enterCode.jsp");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}