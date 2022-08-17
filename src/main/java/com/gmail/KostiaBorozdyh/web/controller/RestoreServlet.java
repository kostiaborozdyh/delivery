package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.service.UserService;
import com.gmail.KostiaBorozdyh.model.utils.Validation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Controller for Restoring password
 */
@WebServlet(name = "RestoreServlet", value = "/restore")
public class RestoreServlet extends HttpServlet {
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

                if (!UserService.loginIsValid(login)) {
                    userLogin = login;
                } else {
                    session.setAttribute("invalidLogin", "invalidLogin");
                }

            }
        } else {

            if (!UserService.emailIsValid(login)) {
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
                String email = UserService.getUserEmailByUserLogin(userLogin);
                session.setAttribute("email", email);
            }
            response.sendRedirect("/restore/restorePassword.jsp");

        }
    }
}
