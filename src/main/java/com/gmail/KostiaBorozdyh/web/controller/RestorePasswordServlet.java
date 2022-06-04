package com.gmail.KostiaBorozdyh.web.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RestorePasswordServlet", value = "/restorePassword")
public class RestorePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("code").equals(request.getSession().getAttribute("code"))) {
            response.sendRedirect("/restore/enterPassword.jsp");
        } else {
            request.getSession().setAttribute("invalidCode", "invalidCode");
            response.sendRedirect("/restore/restorePassword.jsp");
        }
    }
}
