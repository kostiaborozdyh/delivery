package com.example.demos.web.controller;

import com.example.demos.model.UserDao;
import com.example.demos.model.entity.User;

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
        final Integer value = Integer.parseInt(request.getParameter("value"));
        User user = (User) request.getSession().getAttribute("user");
        int money;
        if(user.getMoney()==null) money=0;
        else money = user.getMoney();
        if(UserDao.refillMoney(user.getId(),value,money)){
            user.setMoney(money+value);
            request.getSession().setAttribute("money",money+value);
        }
        response.sendRedirect("user/refill.jsp");
    }
}
