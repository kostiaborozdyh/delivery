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
        final String cityFrom = request.getParameter("cityFrom");
        final String cityTo = request.getParameter("cityTo");
        try {
            List<InfoTable> infoTable = InfoTableDao.getInfoTable(cityFrom,cityTo);
            request.getSession().setAttribute("infoTable",infoTable);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
       response.sendRedirect("/index.jsp");
    }
}
