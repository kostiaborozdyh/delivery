package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.utils.Calculate;
import com.gmail.KostiaBorozdyh.model.utils.Table;
import com.gmail.KostiaBorozdyh.model.entity.InfoTable;
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

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        StringBuilder cityFrom = new StringBuilder();
        StringBuilder cityTo = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            String strFrom = request.getParameter("cityFrom" + (i + 1));
            String strTo = request.getParameter("cityTo" + (i + 1));
            if (strFrom != null){
                cityFrom.append("|").append(strFrom);
            }
            if (strTo != null){
                cityTo.append("|").append(strTo);
            }
        }
        cityFrom.deleteCharAt(0);
        cityTo.deleteCharAt(0);
        try {
            List<InfoTable> infoTable = Table.getInfoTable(cityFrom.toString(), cityTo.toString());
            List<Integer> list = Calculate.getPaginationList(infoTable);

            if (list == null) {
                session.setAttribute("infoTableShort", infoTable);
            } else {
                session.setAttribute("infoTableShort", Calculate.getFiveElements(infoTable, 1));
            }

            session.setAttribute("infoTable", infoTable);
            session.setAttribute("list", list);
            session.setAttribute("pageNumber", 1);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        session.removeAttribute("table");
        response.sendRedirect("/info.jsp");
    }
}
