package com.example.demos.web.controller;

import com.example.demos.model.InfoTableDao;
import com.example.demos.model.entity.InfoTable;
import org.json.simple.parser.ParseException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowInfoServlet", value = "/showInfo")
public class ShowInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder cityFrom = new StringBuilder();
        StringBuilder cityTo = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            String strFrom = request.getParameter("cityFrom"+(i+1));
            String strTo = request.getParameter("cityTo"+(i+1));
            if(strFrom!=null) cityFrom.append("|").append(strFrom);
            if(strTo!=null) cityTo.append("|").append(strTo);
        }
        cityFrom.deleteCharAt(0);
        cityTo.deleteCharAt(0);
            try {
                List<InfoTable> infoTable = InfoTableDao.getInfoTable(cityFrom.toString(), cityTo.toString());
                request.getSession().setAttribute("infoTable", infoTable);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            request.getSession().removeAttribute("table");
       response.sendRedirect("/index.jsp");
    }
}
