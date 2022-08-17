package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.entity.User;
import com.gmail.KostiaBorozdyh.model.dto.ValidListDTO;
import com.gmail.KostiaBorozdyh.model.service.UserService;
import com.gmail.KostiaBorozdyh.model.utils.Validation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Controller for Registration new User
 */
@WebServlet(name = "RegistrationServlet", value = "/registrationUser")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setSecondPassword(request.getParameter("secondPassword"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setPhoneNumber(request.getParameter("phoneNumber"));
        user.setRoleId(1);
        String[] notify = request.getParameterValues("notify");

        if (notify == null) {
            user.setNotify("no");
        } else {
            user.setNotify("yes");
        }

        ValidListDTO validList = Validation.valid(user, true, true);

        boolean userIsValid = Validation.count(validList);

        if (userIsValid) {
            UserService.save(user);
            response.sendRedirect("/login.jsp");
            request.getSession().setAttribute("successful", "successful");
        } else {
            request.getSession().setAttribute("validList", validList);
            response.sendRedirect("/registration.jsp");
        }
    }
}
