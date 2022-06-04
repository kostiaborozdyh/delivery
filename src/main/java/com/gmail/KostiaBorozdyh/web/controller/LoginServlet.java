package com.gmail.KostiaBorozdyh.web.controller;


import com.gmail.KostiaBorozdyh.model.dao.UserDao;
import com.gmail.KostiaBorozdyh.model.entity.User;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/loginUser")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");
        session.removeAttribute("validList");
        User user;
        try {
            user = UserDao.userValid(login, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (user == null) {
            session.setAttribute("invalid", "invalid");
            response.sendRedirect("/login.jsp");
        } else {
            if (user.getBan().equals("yes")) {
                session.setAttribute("invalid", "block");
                response.sendRedirect("/login.jsp");
            } else {
                int roleId = user.getRoleId();
                switch (roleId) {
                    case 1: {
                        session.setAttribute("user", user);
                        session.setAttribute("money", user.getMoney());
                        session.setAttribute("role", "user");
                        response.sendRedirect("/info.jsp");
                    }
                    break;
                    case 2: {
                        session.setAttribute("user", user);
                        session.setAttribute("role", "manager");
                        response.sendRedirect("/man/orderList.jsp");
                    }
                    break;
                    case 3: {
                        session.setAttribute("user", user);
                        session.setAttribute("role", "admin");
                        response.sendRedirect("/adm/usersTable.jsp");
                    }
                    break;
                    case 4: {
                        session.setAttribute("user", user);
                        session.setAttribute("role", "employee");
                        response.sendRedirect("/employee/ordersTable.jsp");
                    }
                    break;
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}