package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.entity.User;
import com.gmail.KostiaBorozdyh.model.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Controller for Refilling User money
 */
@WebServlet(name = "RefillServlet", value = "/refill")
public class RefillServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int value = Integer.parseInt(request.getParameter("value"));
        User user = (User) request.getSession().getAttribute("user");
        int money = 0;

        if (user.getMoney() != null) {
            money = user.getMoney();
        }

        user.setMoney(money + value);

        UserService.changeMoney(user);

        request.getSession().setAttribute("money", user.getMoney());
        response.sendRedirect("/user/refill.jsp");
    }
}
