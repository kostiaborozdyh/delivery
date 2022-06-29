package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.dto.FilterOrderDTO;
import com.gmail.KostiaBorozdyh.model.service.OrderService;
import com.gmail.KostiaBorozdyh.model.utils.FiltrationOrder;
import com.gmail.KostiaBorozdyh.model.entity.Order;
import com.gmail.KostiaBorozdyh.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * Controller for Filter Order
 */
@WebServlet(name = "FiltrationOrderServlet", value = "/filtrationOrder")
public class FiltrationOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        FilterOrderDTO filterOrderDTO = new FilterOrderDTO();

        filterOrderDTO.setMinPrice(request.getParameter("minPrice"));
        filterOrderDTO.setMaxPrice(request.getParameter("maxPrice"));
        filterOrderDTO.setPaymentStatus(request.getParameterValues("paymentStatus"));
        filterOrderDTO.setLocation(request.getParameterValues("locationStatus"));
        filterOrderDTO.setMinDateCreate(request.getParameter("minDateCreate"));
        filterOrderDTO.setMaxDateCreate(request.getParameter("maxDateCreate"));
        filterOrderDTO.setMinDateOfArrival(request.getParameter("minDateOfArrival"));
        filterOrderDTO.setMaxDateOfArrival(request.getParameter("maxDateOfArrival"));
        filterOrderDTO.setCityFrom(request.getParameterValues("cityFrom[]"));
        filterOrderDTO.setCityTo(request.getParameterValues("cityTo[]"));
        filterOrderDTO.setSort(request.getParameter("sort"));

        User user =(User) session.getAttribute("user");
        boolean userHasRoleUser = user.getRoleId() == 1;
        List<Order> orderList = OrderService.getOrderListByUser(user,userHasRoleUser);

        orderList = FiltrationOrder.doFilter(orderList, filterOrderDTO);

        session.setAttribute("filter", filterOrderDTO);
        session.setAttribute("orders", orderList);
        session.removeAttribute("pageNumberOrder");

        if (userHasRoleUser) {
            response.sendRedirect("/user/order.jsp");
        } else {
            response.sendRedirect("/man/orderList.jsp");
        }

    }
}
