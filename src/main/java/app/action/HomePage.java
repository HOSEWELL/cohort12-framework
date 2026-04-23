package app.action;

import app.framework.Cohort12Framework;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/home")
public class HomePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Navbar</title>");

        // CSS
        out.println("<style>");
        out.println("body { margin: 0; font-family: Arial, sans-serif; }");

        out.println(".navbar {");
        out.println("  display: flex;");
        out.println("  justify-content: space-between;");
        out.println("  align-items: center;");
        out.println("  background-color: #222;");
        out.println("  padding: 0 20px;");
        out.println("}");

        out.println(".logo {");
        out.println("  color: white;");
        out.println("  font-size: 20px;");
        out.println("  font-weight: bold;");
        out.println("}");

        out.println(".nav-links { display: flex; }");

        out.println(".nav-links a {");
        out.println("  color: white;");
        out.println("  text-decoration: none;");
        out.println("  padding: 15px 20px;");
        out.println("  transition: background 0.3s;");
        out.println("}");

        out.println(".nav-links a:hover { background-color: #444; }");

        out.println("</style>");
        out.println("</head>");

        out.println("<body>");

        // Navbar HTML
        out.println("<div class='navbar'>");
        out.println("  <div class='logo'>MyApp</div>");
        out.println("  <div class='nav-links'>");
        out.println("      <a href='./home'>Home</a>");
        out.println(Cohort12Framework.generateMenuItem());
        out.println("  </div>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}
