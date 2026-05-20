package app.listeners;


import app.bean.SchoolBean;
import app.utility.bootstrap.Bootstrap;
import jakarta.ejb.EJB;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Inject
    @Any
    private Instance<Bootstrap> bootstraps;

    @EJB
    private SchoolBean schoolBean;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        for (Bootstrap bootstrap : bootstraps)
            bootstrap.process();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("App shutting down...");
    }
}