package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.service.UserService;
import com.gmail.KostiaBorozdyh.model.utils.Validation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Controller for Enter password for restoring password
 */
@WebServlet(name = "EnterPasswordServlet", value = "/enterPassword")
public class EnterPasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String secondPassword = request.getParameter("secondPassword");
        String emailUser = (String) request.getSession().getAttribute("email");

        if (password.equals(secondPassword)) {
            if (Validation.passwordValid(password)) {
                request.getSession().setAttribute("passwordNameInvalid", "pass");
                response.sendRedirect("/restore/enterPassword.jsp");
            } else {
                UserService.changePassword(emailUser,password);
                request.getSession().setAttribute("changePassword", "yes");
                response.sendRedirect("/login.jsp");
            }
        } else {
            request.getSession().setAttribute("passwordInvalid", "pass");
            response.sendRedirect("/restore/enterPassword.jsp");
        }
    }
}
