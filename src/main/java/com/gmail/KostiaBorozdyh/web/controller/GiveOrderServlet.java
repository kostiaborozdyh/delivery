package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.service.UserService;
import com.gmail.KostiaBorozdyh.security.Security;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GiveOrderServlet", value = "/giveOrder")
public class GiveOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String userEmail = UserService.getUserEmailByOrderId(id);
        String code = Security.sendCode(userEmail);
        request.getSession().setAttribute("id", id);
        request.getSession().setAttribute("code", code);
        response.sendRedirect("/employee/enterCode.jsp");
    }
}
