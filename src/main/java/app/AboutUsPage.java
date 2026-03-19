package app;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class AboutUsPage extends GenericServlet {

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html");
        PrintWriter writer = servletResponse.getWriter();

        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>About Us - Training Academy</title>");
        writer.println("<style>");
        writer.println("body { font-family: Arial; margin: 40px; background-color: #f4f6f8; }");
        writer.println("header { background-color: #2c3e50; color: white; padding: 15px; }");
        writer.println("section { margin-top: 20px; padding: 15px; background: white; border-radius: 5px; }");
        writer.println("a { color: #3498db; text-decoration: none; font-weight: bold; }");
        writer.println("</style>");
        writer.println("</head>");

        writer.println("<body>");

// Header
        writer.println("<header>");
        writer.println("<h1>About COHORT 12 Training PORTA</h1>");
        writer.println("</header>");

// Who we are
        writer.println("<section>");
        writer.println("<h2>Who We Are</h2>");
        writer.println("<p>COHORT 12 Training PORTAL is a developer-focused learning platform dedicated to building strong backend engineers.</p>");
        writer.println("<p>We specialize in Java, Spring Boot, and enterprise system design.</p>");
        writer.println("</section>");

// Mission
        writer.println("<section>");
        writer.println("<h2>Our Mission</h2>");
        writer.println("<p>To equip developers with practical, job-ready skills through hands-on training and real-world projects.</p>");
        writer.println("</section>");

// What makes us different
        writer.println("<section>");
        writer.println("<h2>Why Choose Us</h2>");
        writer.println("<ul>");
        writer.println("<li>Real-world project experience</li>");
        writer.println("<li>Mentorship from experienced engineers</li>");
        writer.println("<li>Focus on backend architecture and integration</li>");
        writer.println("<li>Exposure to banking and financial systems</li>");
        writer.println("</ul>");
        writer.println("</section>");

// Contact / CTA
        writer.println("<section>");
        writer.println("<h2>Get Started</h2>");
        writer.println("<p>Join our training programs and take your backend development skills to the next level.</p>");
        writer.println("</section>");

// Navigation
        writer.println("<section>");
        writer.println("<a href=\"./\">&larr; Go back to Home Page</a>");
        writer.println("</section>");

        writer.println("</body>");
        writer.println("</html>");
    }
}
