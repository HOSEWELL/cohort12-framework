package app.action;

import app.framework.PageContent;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home")
public class HomePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StringBuilder html = new StringBuilder();

        /* Hero */
        html.append("<div class='hero'>");
        html.append("<h1>Empowering Learning Through Practical Training</h1>");
        html.append("<p>Manage, track, and deliver impactful training programs with ease.</p>");
        html.append("</div>");

        /* Features */
        html.append("<div class='section'>");
        html.append("<h2>Key Features</h2>");
        html.append("<div class='features'>");

        html.append("<div class='card'>");
        html.append("<h3>Course Management</h3>");
        html.append("<p>Create and organize structured training programs easily.</p>");
        html.append("</div>");

        html.append("<div class='card'>");
        html.append("<h3>Trainer Management</h3>");
        html.append("<p>Assign trainers and monitor their performance.</p>");
        html.append("</div>");

        html.append("<div class='card'>");
        html.append("<h3>Trainee Management</h3>");
        html.append("<p>Track enrollments and trainee progress in real time.</p>");
        html.append("</div>");

        html.append("<div class='card'>");
        html.append("<h3>Performance Tracking</h3>");
        html.append("<p>Analyze outcomes with reports and insights.</p>");
        html.append("</div>");

        html.append("</div>");
        html.append("</div>");

        request.setAttribute(PageContent.CONTENT.name(), html.toString());
        RequestDispatcher rd = request.getRequestDispatcher("./app_page");
        rd.include(request, response);
    }
}
