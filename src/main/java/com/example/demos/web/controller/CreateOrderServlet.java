package com.example.demos.web.controller;

import com.example.demos.model.Calculate;
import com.example.demos.model.CreateMessage;
import com.example.demos.model.OrderDao;
import com.example.demos.model.SendEmail;
import com.example.demos.model.entity.User;

import javax.mail.MessagingException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

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
        Integer volume = Integer.parseInt( height)*Integer.parseInt(length)*Integer.parseInt(width);
        int price;
        if(cityFrom.equals(cityTo))
            price = Calculate.deliveryInOneCity(weight,height,length,width);
        else
            price = Calculate.delivery(cityFrom,cityTo,weight,height,length,width);
         OrderDao.createOrder(info,cityFrom,cityTo,address,volume,price,weight,user.getId());
        if(user.getNotify().equals("yes")){
            String str[] = CreateMessage.messageCreateOrder(cityFrom,cityTo,price);
            try {
                SendEmail.send(user.getEmail(),str[0],str[1]);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        response.sendRedirect("/index.jsp");

    }
}
