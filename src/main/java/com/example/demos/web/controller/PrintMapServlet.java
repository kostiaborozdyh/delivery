package com.example.demos.web.controller;

import com.example.demos.model.entity.InfoTable;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PrintMapServlet", value = "/printMap")
public class PrintMapServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt( request.getParameter("id"));
        List<InfoTable> infoTable = (List<InfoTable>) request.getSession().getAttribute("infoTable");
        request.getSession().setAttribute("table",infoTable.get(id));
        response.sendRedirect("/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
