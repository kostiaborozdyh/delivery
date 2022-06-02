package com.example.demos.web.controller;

import com.example.demos.model.utils.Calculate;
import com.example.demos.model.utils.GoogleMaps;
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
        HttpSession session = request.getSession();
        final String cityFrom = request.getParameter("cityFrom");
        final String cityTo = request.getParameter("cityTo");
        final String weight = request.getParameter("weight");
        final String height = request.getParameter("height");
        final String length = request.getParameter("length");
        final String width = request.getParameter("width");
        final String address = request.getParameter("address");
        final String info = request.getParameter("info");
        int price, volume;
        try {

            List<InfoTable> distanceList = GoogleMaps.getDistance(cityFrom, cityTo);

            volume = Calculate.volume(height, length, width);
            price = Calculate.deliveryPrice(distanceList.get(0).getDistance(), volume, weight);

            InfoTable infoTable = new InfoTable(distanceList.get(0).getCityFrom(), distanceList.get(0).getCityTo(),
                    distanceList.get(0).getDistance(), price, volume, Integer.parseInt(weight));

            if (address != null) {
                session.setAttribute("newOrder", infoTable);
                session.setAttribute("orderAddress", address);
                session.setAttribute("orderInfo", info);
                session.setAttribute("btn", "unblock");
                response.sendRedirect("/user/createOrder.jsp");
            } else {
                session.setAttribute("calculateTable", infoTable);
                response.sendRedirect("/calculate.jsp");
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
