package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.entity.User;
import com.gmail.KostiaBorozdyh.model.entity.ValidList;
import com.gmail.KostiaBorozdyh.model.service.UserService;
import com.gmail.KostiaBorozdyh.model.utils.Validation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddEmployeeServlet", value = "/addEmployee")
public class AddEmployeeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("validList");

        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setSecondPassword(request.getParameter("secondPassword"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setPhoneNumber(request.getParameter("phoneNumber"));
        String employee = request.getParameter("employee");
        user.setNotify("no");

        if (employee.equals("employee")) {
            user.setRoleId(4);
        } else {
            user.setRoleId(2);
        }

        ValidList validList = Validation.valid(user, true,true);

        boolean userIsValid=Validation.count(validList);

        if (userIsValid) {
            UserService.save(user);
            response.sendRedirect("/adm/usersTable.jsp");
        } else {
            request.getSession().setAttribute("validList", validList);
            response.sendRedirect("/adm/createEmployeeAccount.jsp");
        }
    }

}
