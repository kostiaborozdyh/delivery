package com.gmail.KostiaBorozdyh.web.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Controller for Changing city for Employee role
 */
@WebServlet(name = "ChangeCityServlet", value = "/changeCity")
public class ChangeCityServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("city");
        session.removeAttribute("orders");
        session.removeAttribute("pageNumberOrder");
        response.sendRedirect("/employee/ordersTable.jsp");
    }
}
