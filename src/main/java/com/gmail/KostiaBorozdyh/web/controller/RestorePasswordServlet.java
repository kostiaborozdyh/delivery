package com.gmail.KostiaBorozdyh.web.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RestorePasswordServlet", value = "/restorePassword")
public class RestorePasswordServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String trueCode = (String) request.getSession().getAttribute("code");
        if (code.equals(trueCode)) {
            response.sendRedirect("/restore/enterPassword.jsp");
        } else {
            request.getSession().setAttribute("invalidCode", "invalidCode");
            response.sendRedirect("/restore/restorePassword.jsp");
        }
    }
}
