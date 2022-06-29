package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.dto.InfoTableDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * Controller for Printing GoogleMap
 */
@WebServlet(name = "PrintMapServlet", value = "/printMap")
public class PrintMapServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt( request.getParameter("id"));
        List<InfoTableDTO> infoTable = (List<InfoTableDTO>) request.getSession().getAttribute("infoTable");
        for (InfoTableDTO table :
                infoTable) {
            if(table.getId()==id) request.getSession().setAttribute("table",table);
        }
        response.sendRedirect("/info.jsp");
    }
}
