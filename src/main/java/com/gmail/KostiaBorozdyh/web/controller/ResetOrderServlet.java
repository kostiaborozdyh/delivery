package com.gmail.KostiaBorozdyh.web.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Controller for Resetting filtration fields
 */
@WebServlet(name = "ResetOrderServlet", value = "/resetOrder")
public class ResetOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("filter");
        session.removeAttribute("pageNumberOrder");
        session.removeAttribute("orders");

        if (session.getAttribute("role").equals("user")) {
            response.sendRedirect("/user/order.jsp");
        } else {
            response.sendRedirect("/man/orderList.jsp");
        }

    }
}
