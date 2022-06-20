package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.dao.OrderDao;
import com.gmail.KostiaBorozdyh.model.entity.Order;
import com.gmail.KostiaBorozdyh.model.service.OrderService;
import com.gmail.KostiaBorozdyh.model.utils.Calculate;
import com.gmail.KostiaBorozdyh.model.utils.JsonParser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChooseCityServlet", value = "/chooseCity")
public class ChooseCityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String on = request.getParameter("on");

        if(on!=null) {
            session.removeAttribute("pageNumberOrder");
        }

        String city;
        if (session.getAttribute("city") == null) {
            city = request.getParameter("city");
            city = JsonParser.cutCityNameForEmployee(city);
            session.setAttribute("city", city);
        } else {
            city = (String) session.getAttribute("city");
        }

        List<Order> orderList = OrderService.getOrderListByCityOfArrival(city);

        if (session.getAttribute("pageNumberOrder") == null) {
            List<Integer> list = Calculate.getPaginationList(orderList);
            if (list == null) {
                session.setAttribute("shortOrders", orderList);
            } else {
                session.setAttribute("shortOrders", Calculate.getFiveElements(orderList, 1));
            }
            session.setAttribute("listNumberOrder", list);
            session.setAttribute("pageNumberOrder", 1);
        }


        session.setAttribute("orders", orderList);
        response.sendRedirect("/employee/ordersTable.jsp");
    }
}
