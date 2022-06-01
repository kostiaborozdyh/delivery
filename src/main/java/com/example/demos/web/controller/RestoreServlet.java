package com.example.demos.web.controller;

import com.example.demos.model.dao.UserDao;
import com.example.demos.model.utils.Validation;
import com.example.demos.web.filter.RestoreFilter;
import org.apache.log4j.Logger;

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
        HttpSession session = request.getSession();
        String userLogin = null;
        String userEmail = null;
        if (!Validation.emailNameValid(login)) {
            if (!Validation.loginNameValid(login)) {
                session.setAttribute("invalidM", "invalid");
            } else {
                if (!UserDao.loginIsValid(login)) {
                    userLogin = login;
                } else {
                    session.setAttribute("invalidLogin", "invalidLogin");
                }
            }
        } else {
            if (!UserDao.emailIsValid(login)) {
                userEmail = login;
            } else {
                session.setAttribute("invalidEmail", "invalidLEmail");
            }
        }
        if (userEmail == null && userLogin == null) {
            response.sendRedirect("/restore/restore.jsp");
        } else {
            if (userEmail != null) {
                session.setAttribute("email", userEmail);
            } else {
                String result = UserDao.getUserEmail(userLogin);
                session.setAttribute("email", result);
            }
            response.sendRedirect("/restore/restorePassword.jsp");
        }
    }
}
