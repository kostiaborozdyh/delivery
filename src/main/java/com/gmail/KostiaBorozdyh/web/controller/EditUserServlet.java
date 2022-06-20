package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.entity.User;
import com.gmail.KostiaBorozdyh.model.dto.ValidListDTO;
import com.gmail.KostiaBorozdyh.model.service.UserService;
import com.gmail.KostiaBorozdyh.model.utils.Validation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditUserServlet", value = "/editUser")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ValidListDTO validList;
        boolean checkEmail;

        User user = (User) request.getSession().getAttribute("user");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String secondPassword = request.getParameter("secondPassword");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String[] notify = request.getParameterValues("notify");

        User user2 = user.cloneUser();

        if (notify == null) {
            user2.setNotify("no");
        } else {
            user2.setNotify("yes");
        }

        if (email.equals(user2.getEmail()) || email.equals("")) {
            checkEmail = false;
        } else {
            user2.setEmail(email);
            checkEmail = true;
        }

        if (!firstName.equals("")) {
            user2.setFirstName(firstName);
        }
        if (!lastName.equals("")) {
            user2.setLastName(lastName);
        }
        if (!phoneNumber.equals("")) {
            user2.setPhoneNumber(phoneNumber);
        }
        if (!email.equals("")) {
            user2.setEmail(email);
        }

        user2.setPassword(password);
        user2.setSecondPassword(secondPassword);
        validList = Validation.valid(user2, false, checkEmail);
        boolean noErrorsInValidation = Validation.count(validList);

        if (noErrorsInValidation) {
            user = UserService.editUser(user2);
            request.getSession().setAttribute("user", user);
            request.getSession().removeAttribute("validList");

        } else{
            request.getSession().setAttribute("validList", validList);
        }

        response.sendRedirect("/editUser.jsp");

    }
}
