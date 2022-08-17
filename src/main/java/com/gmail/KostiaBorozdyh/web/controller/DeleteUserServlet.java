package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Controller for Deleting User
 */
@WebServlet(name = "DeleteUserServlet", value = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        UserService.deleteUser(id);
        request.getSession().removeAttribute("pageNumberUser");
        response.sendRedirect("/adm/usersTable.jsp");
    }
}
