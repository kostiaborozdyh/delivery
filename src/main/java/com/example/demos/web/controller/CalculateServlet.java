package com.example.demos.web.controller;

import com.example.demos.model.Calculate;
import com.example.demos.model.GoogleMaps;
import com.example.demos.model.entity.Distance;
import com.example.demos.model.entity.InfoTable;
import org.json.simple.parser.ParseException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/calculateBag")
public class CalculateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/calculate.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String cityFrom = request.getParameter("cityFrom");
        final String cityTo = request.getParameter("cityTo");
        final String weight = request.getParameter("weight");
        final String height = request.getParameter("height");
        final String length = request.getParameter("length");
        final String width = request.getParameter("width");
        int price,volume;
        try {
            List<Distance> distanceList = GoogleMaps.getDistance(cityFrom,cityTo);
            price = Calculate.deliveryPrice(distanceList.get(0).getDistance(),weight,height,length,width);
            volume = Calculate.volume(height,length,width);
            InfoTable infoTable = new InfoTable(distanceList.get(0).getCityFrom(),distanceList.get(0).getCityTo(),distanceList.get(0).getDistance(),price,volume,Integer.parseInt(weight));
            request.getSession().setAttribute("calculateTable",infoTable);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/calculate.jsp");
    }
}
