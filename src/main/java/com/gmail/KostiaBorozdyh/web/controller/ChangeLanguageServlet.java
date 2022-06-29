package com.gmail.KostiaBorozdyh.web.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Controller for Changing language
 */
@WebServlet(name = "ChangeLanguageServlet", value = "/changeLanguage")
public class ChangeLanguageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        if(session.getAttribute("firstLang").equals("ua")) {
            session.setAttribute("firstLang","en");
            session.setAttribute("secondLang","ua");
            session.setAttribute("lang","end");
        } else {
            session.setAttribute("firstLang","ua");
            session.setAttribute("secondLang","en");
            session.setAttribute("lang","uk");
        }
        switch (id){
            case 1: response.sendRedirect("/index.jsp");                                break;
            case 2: response.sendRedirect("/aboutUs.jsp");                              break;
            case 3: response.sendRedirect("/info.jsp");                                 break;
            case 4: response.sendRedirect("/calculate.jsp");                            break;
            case 5: response.sendRedirect("/editUser.jsp");                             break;
            case 6: response.sendRedirect("/error.jsp");                                break;
            case 7: response.sendRedirect("/errorServer.jsp");                          break;
            case 8: response.sendRedirect("/login.jsp");                                break;
            case 9: response.sendRedirect("/registration.jsp");                         break;
            case 10: response.sendRedirect("/reviews.jsp");                             break;
            case 11: response.sendRedirect("/adm/createEmployeeAccount.jsp");           break;
            case 12: response.sendRedirect("/adm/usersTable.jsp");                      break;
            case 13: response.sendRedirect("/employee/acceptOrder.jsp");                break;
            case 14: response.sendRedirect("/employee/enterCode.jsp");                  break;
            case 15: response.sendRedirect("/employee/ordersTable.jsp");                break;
            case 16: response.sendRedirect("/man/orderList.jsp");                       break;
            case 17: response.sendRedirect("/man/orderUser.jsp");                       break;
            case 18: response.sendRedirect("/restore/enterPassword.jsp");               break;
            case 19: response.sendRedirect("/restore/restore.jsp");                     break;
            case 20: response.sendRedirect("/restore/restorePassword.jsp");             break;
            case 21: response.sendRedirect("/user/createOrder.jsp");                    break;
            case 22: response.sendRedirect("/user/location.jsp");                       break;
            case 23: response.sendRedirect("/user/order.jsp");                          break;
            case 24: response.sendRedirect("/user/refill.jsp");                         break;
            case 25: response.sendRedirect("/user/userOrder.jsp");                      break;
        }
    }
}
