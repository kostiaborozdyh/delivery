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
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        int fun = Integer.parseInt(request.getParameter("fun"));
        switch (fun) {
            case 1: {
                List<InfoTable> infoTable = (List<InfoTable>) session.getAttribute("infoTable");
                List<Integer> list = (List<Integer>) session.getAttribute("list");
                id = Calculate.pageId(id, list);
                session.setAttribute("infoTableShort", Calculate.getFiveElements(infoTable, id));
                session.setAttribute("pageNumber", id);
                response.sendRedirect("/info.jsp");
            }
            break;
            case 2: {
                List<Order> orderList = (List<Order>) session.getAttribute("orders");
                List<Integer> list = (List<Integer>) session.getAttribute("listNumberOrder");
                id = Calculate.pageId(id, list);
                session.setAttribute("shortOrders", Calculate.getFiveElements(orderList, id));
                session.setAttribute("pageNumberOrder", id);
                String role = (String) session.getAttribute("role");
                switch (role) {
                    case "user":
                        response.sendRedirect("/user/order.jsp");
                        break;
                    case "manager":
                        response.sendRedirect("/man/orderList.jsp");
                        break;
                    case "employee":
                        response.sendRedirect("/employee/ordersTable.jsp");
                        break;
                }
                break;
            }
            case 3: {
                List<User> userList = (List<User>) session.getAttribute("userList");
                List<Integer> list = (List<Integer>) session.getAttribute("listNumberUser");
                id = Calculate.pageId(id, list);
                session.setAttribute("shortUsers", Calculate.getFiveElements(userList, id));
                session.setAttribute("pageNumberUser", id);
                response.sendRedirect("/adm/usersTable.jsp");
            }
            break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
