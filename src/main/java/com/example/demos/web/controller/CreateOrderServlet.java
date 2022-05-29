package com.example.demos.web.controller;

import com.example.demos.model.dao.OrderDao;
import com.example.demos.model.entity.Distance;
import com.example.demos.model.entity.InfoTable;
import com.example.demos.model.entity.User;
import com.example.demos.model.utils.Calculate;
import com.example.demos.model.utils.CreateMessage;
import com.example.demos.model.utils.GoogleMaps;
import com.example.demos.model.utils.SendEmail;
import org.json.simple.parser.ParseException;

import javax.mail.MessagingException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CreateOrderServlet", value = "/createOrder")
public class CreateOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        InfoTable infoTable = (InfoTable) request.getSession().getAttribute("newOrder");

        final String info = (String) request.getSession().getAttribute("orderInfo");
        final String cityFrom = infoTable.getCityFrom();
        final String cityTo = infoTable.getCityTo();
        final String address = (String) request.getSession().getAttribute("orderAddress");
        final String weight = infoTable.getWeight().toString();
        User user = (User) request.getSession().getAttribute("user");
        int volume = infoTable.getVolume();
        int price;
        List<Distance> distanceList;
        try {
            distanceList = GoogleMaps.getDistance(cityFrom, cityTo);
            price = Calculate.deliveryPrice(distanceList.get(0).getDistance(), volume, weight);
            OrderDao.createOrder(info, distanceList.get(0).getCityFrom(), distanceList.get(0).getCityTo(),
                    address, price, volume, weight, distanceList.get(0).getDistance(), user.getId());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if (user.getNotify().equals("yes")) {
            String[] str = CreateMessage.messageCreateOrder(cityFrom, cityTo, distanceList.get(0).getDistance(), price);
            try {
                SendEmail.send(user.getEmail(), str[0], str[1]);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        request.getSession().removeAttribute("newOrder");
        request.getSession().removeAttribute("orderInfo");
        request.getSession().removeAttribute("orderAddress");
        request.getSession().removeAttribute("btn");
        response.sendRedirect("/user/order.jsp");

    }
}
