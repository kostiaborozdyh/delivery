package com.example.demos.web.controller;

import com.example.demos.model.dao.UserDao;
import com.example.demos.model.entity.User;
import com.example.demos.model.entity.ValidList;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditUserServlet", value = "/editUser")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         ValidList validList;
         int c = 3;
         User user = (User) request.getSession().getAttribute("user");
         String firstName = request.getParameter("firstName");
         String lastName = request.getParameter("lastName");
         String password = request.getParameter("password");
         String phoneNumber = request.getParameter("phoneNumber");
         String email = request.getParameter("email");
         String[] notify = request.getParameterValues("notify");
         User user2;
         try {
            user2 = (User) user.clone();
         } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
         }
         if(notify==null) user2.setNotify("no");
         else user2.setNotify("yes");
         if(email.equals(user2.getEmail()) || email.equals("")) c =2;
         if(!firstName.equals("")) user2.setFirstName(firstName);
         if(!lastName.equals(""))  user2.setLastName(lastName);
         if(!phoneNumber.equals("")) user2.setPhoneNumber(phoneNumber);
         if(!email.equals("")) user2.setEmail(email);
         if(password.equals("")) {
             password = "Password1";
             user2.setPassword(password);
             validList = UserDao.valid(user2,password,c);
         }
         else {
             user2.setPassword(password);
             validList = UserDao.valid(user2,request.getParameter("secondPassword"),c);
         }

         if (UserDao.validation(validList)) {
            user = UserDao.editUser(user2);
             if (user!=null) {
                 request.getSession().setAttribute("user", user);
                 request.getSession().removeAttribute("validList");
             }

         }
         else  request.getSession().setAttribute("validList",validList);

         response.sendRedirect("/editUser.jsp");

    }
}
