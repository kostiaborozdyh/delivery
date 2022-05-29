package com.example.demos.web.controller;

import com.example.demos.model.utils.Calculate;
import com.example.demos.model.entity.Point;
import org.json.simple.parser.ParseException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ShowLocationServlet", value = "/showLocation")
public class ShowLocationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        try {
            Point currentPoint = Calculate.getPointAtTheMoment(id);
            request.getSession().setAttribute("latitude", currentPoint.getLatitude());
            request.getSession().setAttribute("longitude", currentPoint.getLongitude());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/user/location.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
