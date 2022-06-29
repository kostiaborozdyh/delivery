package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.dto.InfoTableDTO;
import com.gmail.KostiaBorozdyh.model.service.InfoTableService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * Controller for Showing info about distance between cities
 */
@WebServlet(name = "ShowInfoServlet", value = "/showInfo")
public class ShowInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String cityFrom = getRequestCityString(request, "cityFrom");
        String cityTo = getRequestCityString(request, "cityTo");


        List<InfoTableDTO> infoTableList = InfoTableService.getInfoTable(cityFrom, cityTo);
        List<Integer> pageNumberList = InfoTableService.getPaginationList(infoTableList);
        List<InfoTableDTO> shortInfoTableList = InfoTableService.getShortInfoTable(infoTableList, pageNumberList);


        session.setAttribute("infoTable", infoTableList);
        session.setAttribute("list", pageNumberList);
        session.setAttribute("pageNumber", 1);
        session.setAttribute("infoTableShort", shortInfoTableList);

        session.removeAttribute("table");
        response.sendRedirect("/info.jsp");
    }

    private String getRequestCityString(HttpServletRequest request, String city) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            String string = request.getParameter(city + (i + 1));
            if (!string.equals("")) {
                stringBuilder.append("|").append(string);
            }
        }
        stringBuilder.deleteCharAt(0);
        return stringBuilder.toString();
    }
}
