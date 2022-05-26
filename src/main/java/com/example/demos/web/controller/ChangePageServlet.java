package com.example.demos.web.controller;

import com.example.demos.model.Calculate;
import com.example.demos.model.entity.InfoTable;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChangePageServlet", value = "/changePage")
public class ChangePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<InfoTable> infoTable = (List<InfoTable>) request.getSession().getAttribute("infoTable");
        List<Integer> list = (List<Integer>) request.getSession().getAttribute("list");
        System.out.println(list.size());
        if(id==0) {
            id=1;
        } else if (id==list.size()+1) {
            id=list.size();
        }
        request.getSession().setAttribute("infoTableShort", Calculate.getFiveElements(infoTable, id));
        request.getSession().setAttribute("pageNumber",id);
        response.sendRedirect("/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
