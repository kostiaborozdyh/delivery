package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.dto.InfoTableDTO;
import com.gmail.KostiaBorozdyh.model.entity.Order;
import com.gmail.KostiaBorozdyh.model.service.UserService;
import com.gmail.KostiaBorozdyh.model.utils.Calculate;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * Controller for Changing page
 */
@WebServlet(name = "ChangePageServlet", value = "/changePage")
public class ChangePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        int fun = Integer.parseInt(request.getParameter("fun"));
        switch (fun) {
            case 1: {
                List<InfoTableDTO> infoTable = (List<InfoTableDTO>) session.getAttribute("infoTable");
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
                List<Integer> list = (List<Integer>) session.getAttribute("listNumberUser");
                id = Calculate.pageId(id, list);
                session.setAttribute("userList", UserService.getUsersWithLimit((id-1)*5));
                session.setAttribute("pageNumberUser", id);
                response.sendRedirect("/adm/usersTable.jsp");
            }
            break;
        }
    }
}
