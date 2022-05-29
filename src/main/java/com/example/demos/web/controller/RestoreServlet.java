package com.example.demos.web.controller;

import com.example.demos.model.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RestoreServlet", value = "/restore")
public class RestoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        request.getSession().invalidate();
        String userLogin = null;
        String userEmail = null;
        if (!UserDao.emailNameValid(login)) {
            if (!UserDao.loginNameValid(login)) {
                request.getSession().setAttribute("invalidM", "invalid");
            } else {
                if (!UserDao.loginIsValid(login)) {
                    userLogin = login;
                } else {
                    request.getSession().setAttribute("invalidLogin", "invalidLogin");
                }
            }
        } else {
            if (!UserDao.emailIsValid(login)) {
                userEmail = login;
            } else {
                request.getSession().setAttribute("invalidEmail", "invalidLEmail");
            }
        }

        if (userEmail == null && userLogin == null) {
            response.sendRedirect("/restore/restore.jsp");
        } else {
            if (userEmail != null) {
                request.getSession().setAttribute("email", userEmail);
            } else {
                request.getSession().setAttribute("email", UserDao.getUserEmail(userLogin));
            }
            response.sendRedirect("/restore/restorePassword.jsp");
        }
    }
}
