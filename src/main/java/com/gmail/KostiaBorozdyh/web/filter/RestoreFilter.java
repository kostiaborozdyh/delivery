package com.gmail.KostiaBorozdyh.web.filter;

import com.gmail.KostiaBorozdyh.security.Security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter for Restore password
 */
public class RestoreFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (session.getAttribute("email") == null) {
            response.sendRedirect("/info.jsp");
        } else {
            if (session.getAttribute("first") == null) {
                String email = (String) session.getAttribute("email");
                session.setAttribute("code", Security.restorePassword(email));
                session.setAttribute("first", "invalid");
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
