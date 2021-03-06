package com.gmail.KostiaBorozdyh.web.listener;

import com.gmail.KostiaBorozdyh.DB.DBHelper;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Listener for working wit data base
 */
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