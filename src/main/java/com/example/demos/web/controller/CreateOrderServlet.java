package com.example.demos.web.controller;

import com.example.demos.model.dao.OrderDao;
import com.example.demos.model.entity.InfoTable;
import com.example.demos.model.entity.Order;
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
        HttpSession session = request.getSession();

        InfoTable infoTable = (InfoTable) session.getAttribute("newOrder");
        final String info = (String) session.getAttribute("orderInfo");
        final String address = (String) session.getAttribute("orderAddress");

        User user = (User) session.getAttribute("user");
        OrderDao.createOrder(new Order(infoTable,info,address,user.getId()));

        if (user.getNotify().equals("yes")) {
            try {
                SendEmail.send(user.getEmail(), CreateMessage.messageCreateOrder(infoTable));
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }

        session.removeAttribute("newOrder");
        session.removeAttribute("orderInfo");
        session.removeAttribute("orderAddress");
        session.removeAttribute("btn");
        response.sendRedirect("/user/order.jsp");
    }
}
