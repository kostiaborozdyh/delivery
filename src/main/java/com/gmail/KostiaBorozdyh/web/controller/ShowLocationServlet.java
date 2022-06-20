package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.utils.Calculate;
import com.gmail.KostiaBorozdyh.model.entity.Point;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ShowLocationServlet", value = "/showLocation")
public class ShowLocationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));

        Point currentPoint = Calculate.getPointAtTheMoment(id);

        request.getSession().setAttribute("latitude", currentPoint.getLatitude());
        request.getSession().setAttribute("longitude", currentPoint.getLongitude());

        response.sendRedirect("/user/location.jsp");
    }
}
