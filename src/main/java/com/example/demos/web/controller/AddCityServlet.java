package com.example.demos.web.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddCityServlet", value = "/addCity")
public class AddCityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        if(id==1) {
            if (session.getAttribute("numberCityFrom") == null) session.setAttribute("numberCityFrom", 2);
            else {
                int numb = (int) session.getAttribute("numberCityFrom");
                switch (numb) {
                    case 2:
                        session.setAttribute("numberCityFrom", 3);
                        break;
                    case 3:
                        session.setAttribute("numberCityFrom", 4);
                        break;
                    case 4:
                        session.setAttribute("numberCityFrom", 5);
                        break;
                }
            }
        }
        else {
            if (session.getAttribute("numberCityTo") == null) session.setAttribute("numberCityTo", 2);
            else {
                int numb = (int) session.getAttribute("numberCityTo");
                switch (numb) {
                    case 2:
                        session.setAttribute("numberCityTo", 3);
                        break;
                    case 3:
                        session.setAttribute("numberCityTo", 4);
                        break;
                    case 4:
                        session.setAttribute("numberCityTo", 5);
                        break;
                }
            }
        }
        response.sendRedirect("/index.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
