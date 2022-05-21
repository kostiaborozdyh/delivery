package com.example.demos.web.controller;

import com.example.demos.model.*;
import com.example.demos.model.entity.Distance;
import com.example.demos.model.entity.User;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        final String info = request.getParameter("info");
        final String cityFrom = request.getParameter("cityFrom");
        final String cityTo = request.getParameter("cityTo");
        final String address = request.getParameter("address");
        final String weight = request.getParameter("weight");
        final String height =  request.getParameter("height");
        final String length =  request.getParameter("length");
        final String width =  request.getParameter("width");
        User user = (User) request.getSession().getAttribute("user");
        int volume = Calculate.volume(height,length,width);
        int price;
        List<Distance> distanceList;
        try {
            distanceList = GoogleMaps.getDistance(cityFrom,cityTo);
            price = Calculate.deliveryPrice(distanceList.get(0).getDistance(),volume,weight);
            OrderDao.createOrder(info,distanceList.get(0).getCityFrom(),distanceList.get(0).getCityTo(),address,price,volume,weight,distanceList.get(0).getDistance(),user.getId());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if(user.getNotify().equals("yes")){
            String[] str = CreateMessage.messageCreateOrder(cityFrom,cityTo,distanceList.get(0).getDistance(),price);
            try {
                SendEmail.send(user.getEmail(),str[0],str[1]);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        response.sendRedirect("/index.jsp");

    }
}
