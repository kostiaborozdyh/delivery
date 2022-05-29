package com.example.demos.web.controller;

import com.example.demos.model.entity.Order;
import com.example.demos.model.entity.User;
import com.example.demos.model.utils.Calculate;
import com.example.demos.model.entity.InfoTable;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChangePageServlet", value = "/changePage")
public class ChangePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fun = request.getParameter("fun");
        if (fun == null) {
            List<InfoTable> infoTable = (List<InfoTable>) request.getSession().getAttribute("infoTable");
            List<Integer> list = (List<Integer>) request.getSession().getAttribute("list");
            if (id == 0) {
                id = 1;
            } else if (id == list.size() + 1) {
                id = list.size();
            }
            request.getSession().setAttribute("infoTableShort", Calculate.getFiveElements(infoTable, id));
            request.getSession().setAttribute("pageNumber", id);
            response.sendRedirect("/info.jsp");
        } else if (Integer.parseInt(fun)==2) {
            List<Order> orderList = (List<Order>) request.getSession().getAttribute("orders");
            List<Integer> list = (List<Integer>) request.getSession().getAttribute("listNumberOrder");
            if (id == 0) {
                id = 1;
            } else if (id == list.size() + 1) {
                id = list.size();
            }
            request.getSession().setAttribute("shortOrders", Calculate.getFiveElements(orderList, id));
            request.getSession().setAttribute("pageNumberOrder", id);
            String role = (String) request.getSession().getAttribute("role");
            switch (role) {
                case "user": response.sendRedirect("/user/order.jsp"); break;
                case "manager": response.sendRedirect("/man/orderList.jsp"); break;
                case "employee":  response.sendRedirect("/employee/ordersTable.jsp"); break;
            }
        } else if (Integer.parseInt(fun)==3) {
            List<User> userList = (List<User>) request.getSession().getAttribute("userList");
            List<Integer> list = (List<Integer>) request.getSession().getAttribute("listNumberUser");
            if (id == 0) {
                id = 1;
            } else if (id == list.size() + 1) {
                id = list.size();
            }
            request.getSession().setAttribute("shortUsers", Calculate.getFiveElements(userList, id));
            request.getSession().setAttribute("pageNumberUser", id);
            response.sendRedirect("/adm/usersTable.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
