package com.example.demos.web.controller;

import com.example.demos.model.dao.UserDao;
import com.example.demos.model.entity.User;
import com.example.demos.model.entity.ValidList;
import com.example.demos.model.utils.Validation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddEmployeeServlet", value = "/addEmployee")
public class AddEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

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

        if (Validation.count(validList)) {
            UserDao.insertUser(user);
            response.sendRedirect("/adm/usersTable.jsp");
        } else {
            request.getSession().setAttribute("validList", validList);
            response.sendRedirect("/adm/createEmployeeAccount.jsp");
        }
    }

}
