package com.example.demos.web.controller;

import com.example.demos.model.OrderDao;
import com.example.demos.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ResetOrderServlet", value = "/resetOrder")
public class ResetOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("minPrice");
        session.removeAttribute("maxPrice");
        session.removeAttribute("status1");
        session.removeAttribute("status2");
        session.removeAttribute("status3");
        session.removeAttribute("minDateCreate");
        session.removeAttribute("maxDateCreate");
        session.removeAttribute("minDateOfArrival");
        session.removeAttribute("maxDateOfArrival");
        session.removeAttribute("cityFrom");
        session.removeAttribute("cityTo");
        session.removeAttribute("cityFromSet");
        session.removeAttribute("cityToSet");
        session.removeAttribute("sort");
        if(session.getAttribute("role").equals("user")) {
            session.setAttribute("orders", OrderDao.getUserOrders((User) request.getSession().getAttribute("user")));
            response.sendRedirect("/user/order.jsp");
        }
        else {
            session.setAttribute("orderList", OrderDao.getOrderList());
            response.sendRedirect("/man/orderList.jsp");
        }
    }
}
