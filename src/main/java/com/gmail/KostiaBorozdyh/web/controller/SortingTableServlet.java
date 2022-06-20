package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.service.InfoTableService;
import com.gmail.KostiaBorozdyh.model.utils.Calculate;
import com.gmail.KostiaBorozdyh.model.utils.FiltrationOrder;
import com.gmail.KostiaBorozdyh.model.entity.InfoTable;

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

        List<InfoTable> infoTableList = (List<InfoTable>) session.getAttribute("infoTable");
        List<Integer> pageNumberList = (List<Integer>) session.getAttribute("list");

        infoTableList = FiltrationOrder.sortingTable(sort, infoTableList);

        List<InfoTable> shortInfoTableList = InfoTableService.getShortInfoTable(infoTableList, pageNumberList);

        session.setAttribute("infoTable", infoTableList);
        session.setAttribute("shortInfoTable", shortInfoTableList);
        session.setAttribute("pageNumber", 1);
        session.setAttribute("sort", sort);

        response.sendRedirect("/info.jsp");
    }
}
