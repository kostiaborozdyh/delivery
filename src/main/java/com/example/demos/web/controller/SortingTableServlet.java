package com.example.demos.web.controller;

import com.example.demos.model.Calculate;
import com.example.demos.model.FiltrationOrder;
import com.example.demos.model.entity.InfoTable;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SortingTableServlet", value = "/sortingTable")
public class SortingTableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sort = request.getParameter("sort");
        HttpSession session = request.getSession();
        List<InfoTable> tableList = (List<InfoTable>) session.getAttribute("infoTable");
        tableList = FiltrationOrder.sortingTable(sort, tableList);
        session.setAttribute("infoTable", tableList);
        session.setAttribute("infoTableShort", Calculate.getFiveElements(tableList, 1));
        session.setAttribute("pageNumber", 1);
        session.setAttribute("sort", sort);
        response.sendRedirect("/index.jsp");
    }
}
