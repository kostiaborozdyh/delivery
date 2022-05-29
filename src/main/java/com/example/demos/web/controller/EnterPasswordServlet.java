package com.example.demos.web.controller;

import com.example.demos.model.dao.UserDao;
import com.example.demos.model.utils.Validation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EnterPasswordServlet", value = "/enterPassword")
public class EnterPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("password").equals(request.getParameter("secondPassword"))) {
            if (Validation.passwordValid(request.getParameter("password"))) {
                request.getSession().setAttribute("passwordNameInvalid", "pass");
                response.sendRedirect("/restore/enterPassword.jsp");
            } else {
                if (UserDao.changePassword((String) request.getSession().getAttribute("email"), request.getParameter("password"))) {
                    request.getSession().setAttribute("changePassword", "yes");
                    response.sendRedirect("/login.jsp");
                }
            }
        } else {
            request.getSession().setAttribute("passwordInvalid", "pass");
            response.sendRedirect("/restore/enterPassword.jsp");
        }
    }
}
