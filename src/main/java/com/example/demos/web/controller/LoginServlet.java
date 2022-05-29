package com.example.demos.web.controller;


import com.example.demos.model.dao.UserDao;
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
        User user;
        try {
            user = UserDao.userValid(login, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        HttpSession session = request.getSession();
        if(user==null) {
            session.setAttribute("invalid","invalid");
            response.sendRedirect("/login.jsp");
        }
        else
            if(user.getBan().equals("yes")) {
                session.setAttribute("invalid","block");
                response.sendRedirect("/login.jsp");
            }
            else {
                if (user.getRole_id() == 2) {

                        session.setAttribute("user", user);
                        session.setAttribute("role", "manager");
                        response.sendRedirect("/man/orderList.jsp");
                }
                if (user.getRole_id() == 1) {
                        session.setAttribute("user", user);
                        session.setAttribute("money", user.getMoney());
                        session.setAttribute("role", "user");
                        response.sendRedirect("/info.jsp");
                }
                if(user.getRole_id()==3){
                        session.setAttribute("user",user);
                        session.setAttribute("role","admin");
                        response.sendRedirect("/adm/usersTable.jsp");
                }
                if(user.getRole_id()==4){
                        session.setAttribute("user",user);
                        session.setAttribute("role","employee");
                        response.sendRedirect("/employee/ordersTable.jsp");
                }

            }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.sendRedirect("/info.jsp");
    }
}