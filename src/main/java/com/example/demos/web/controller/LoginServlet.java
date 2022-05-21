package com.example.demos.web.controller;

import com.example.demos.model.SendEmail;
import com.example.demos.model.UserDao;
import com.example.demos.model.entity.User;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/loginUser")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");
        User user = null;
        try {
            user = UserDao.userValid(login, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(user==null) {
            request.getSession().setAttribute("invalid","invalid");
            response.sendRedirect("/login.jsp");
        }
        else {
            if (user.getRole_id() == 2) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("role", "manager");
                response.sendRedirect("/man/orderList.jsp");
            } else {
                if (user.getRole_id() == 1) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    session.setAttribute("money", user.getMoney());
                    session.setAttribute("role", "user");
                    response.sendRedirect("/index.jsp");
                } else {
                    response.sendRedirect("/problemEnter.jsp");
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/index.jsp");
    }
}