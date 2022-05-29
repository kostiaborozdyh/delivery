package com.example.demos.web.listener;

import com.example.demos.DB.DBHelper;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        DBHelper.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        DBHelper.getInstance().shutdown();
    }

}