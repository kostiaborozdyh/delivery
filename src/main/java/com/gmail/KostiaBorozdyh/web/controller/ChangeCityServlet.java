package com.gmail.KostiaBorozdyh.web.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChangeCityServlet", value = "/changeCity")
public class ChangeCityServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("city");
        session.removeAttribute("orders");
        session.removeAttribute("pageNumberOrder");
        response.sendRedirect("/employee/ordersTable.jsp");
    }
}
