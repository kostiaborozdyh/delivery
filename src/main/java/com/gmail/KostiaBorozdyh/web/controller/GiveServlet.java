package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Controller for Giving Order to user for Employee role
 */
@WebServlet(name = "GiveServlet", value = "/give")
public class GiveServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = (Integer) request.getSession().getAttribute("id");
        String code = request.getParameter("code");
        String trueCode = (String) request.getSession().getAttribute("code");
        if (code.equals(trueCode)) {
            OrderService.giveOrder(id);
            response.sendRedirect("/chooseCity");
        } else {
            request.getSession().setAttribute("invalidCode", "invalidCode");
            response.sendRedirect("/employee/enterCode.jsp");
        }
    }
}
