package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.dao.UserDao;
import com.gmail.KostiaBorozdyh.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RefillServlet", value = "/refill")
public class RefillServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int value = Integer.parseInt(request.getParameter("value"));
        User user = (User) request.getSession().getAttribute("user");
        int money;

        if (user.getMoney() == null) {
            money = 0;
        } else {
            money = user.getMoney();
        }

        UserDao.refillMoney(user.getId(), value, money);
        user.setMoney(money + value);

        request.getSession().setAttribute("money", money + value);
        response.sendRedirect("/user/refill.jsp");
    }
}
