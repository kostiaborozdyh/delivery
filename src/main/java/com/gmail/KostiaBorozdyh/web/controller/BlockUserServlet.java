package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "BlockUserServlet", value = "/blockUser")
public class BlockUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        UserService.blockUser(id);
        request.getSession().removeAttribute("pageNumberUser");
        response.sendRedirect("/adm/usersTable.jsp");
    }
}
