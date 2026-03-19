package app;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;

public class WelcomePage implements Servlet {

    public void init(ServletConfig servletConfig) throws ServletException {
        //called when servlet is starting
        System.out.println("Servlet Started and initialized");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html");
        PrintWriter writer = servletResponse.getWriter(); //solid? or pattern ?
        writer.println("<html>");
        writer.println("<body>");
        writer.println("<h1>This is our first servlet through servlet interface</h1>");
        writer.println("</body>");
        writer.println("</html>");

    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        //called when servlet stopped/removed from the container
        System.out.println("Servlet shutting down!!");
    }
}
