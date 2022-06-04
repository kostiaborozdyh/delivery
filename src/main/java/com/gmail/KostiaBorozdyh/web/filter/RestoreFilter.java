package com.gmail.KostiaBorozdyh.web.filter;

import com.gmail.KostiaBorozdyh.security.Security;

import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestoreFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getSession().getAttribute("email") == null) {
            response.sendRedirect("/info.jsp");
        } else {
            if (request.getSession().getAttribute("first") == null) {
                try {
                    request.getSession().setAttribute("code", Security.restorePassword((String) request.getSession().getAttribute("email")));
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
                request.getSession().setAttribute("first", "invalid");
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
