package cz.eclub.iot;

import cz.eclub.iot.model.DbUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyAppServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DbUtils.getInstance().getSessionFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
