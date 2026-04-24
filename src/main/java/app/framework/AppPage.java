package app.framework;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/app_page")
public class AppPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Cohort Training</title>");

        out.println("<style>");
        out.println(":root { --primary:#2563eb; --dark:#0f172a; --light:#f8fafc; }");
        out.println("*{margin:0;padding:0;box-sizing:border-box;}");
        out.println("body{font-family:Segoe UI,Roboto,Arial,sans-serif;background:var(--light);}");

        /* NAVBAR */
        out.println(".navbar{display:flex;justify-content:space-between;align-items:center;padding:15px 30px;background:var(--dark);color:#fff;}");
        out.println(".logo{font-size:22px;font-weight:600;}");
        out.println(".nav-links{display:flex;gap:10px;}");
        out.println(".nav-links a{color:#fff;text-decoration:none;padding:10px 15px;border-radius:6px;transition:.3s;}");
        out.println(".nav-links a:hover{background:var(--primary);}");


        /* CONTENT */
        out.println(".container{max-width:900px;margin:40px auto;padding:20px;}");


        /* CARD */
        out.println(".card{background:#fff;padding:20px;border-radius:10px;box-shadow:0 4px 12px rgba(0,0,0,.08);}");


        /* TABLE */
        out.println("table{width:100%;border-collapse:collapse;margin-top:15px;}");
        out.println("th,td{padding:12px;text-align:left;}");
        out.println("th{background:var(--primary);color:#fff;}");
        out.println("tr:nth-child(even){background:#f1f5f9;}");

        /* FORM */
        out.println(".form-group{margin-bottom:15px;}");
        out.println("label{display:block;margin-bottom:5px;font-weight:500;}");
        out.println("input, select{width:100%;padding:10px;border:1px solid #ccc;border-radius:8px;font-size:14px;}");
        out.println("input:focus, select:focus{outline:none;border-color:var(--primary);box-shadow:0 0 0 2px rgba(37,99,235,0.2);}");

        /* BUTTON */
        out.println(".btn{display:inline-block;padding:10px 15px;background:var(--primary);color:#fff;border:none;border-radius:8px;cursor:pointer;font-size:14px;transition:.3s;}");
        out.println(".btn:hover{background:#1d4ed8;}");

        /* LINK */
        out.println(".back-link{display:inline-block;margin-top:20px;text-decoration:none;color:var(--primary);font-weight:500;}");
        out.println(".back-link:hover{text-decoration:underline;}");

        out.println("</style>");
        out.println("</head>");

        out.println("<body>");

        /* NAVBAR */
        out.println("<div class='navbar'>");
        out.println("<div class='logo'>Cohort Training | User ");
        out.println(request.getSession().getAttribute("UserActualName"));
        out.println("</div>");
        out.println("<div class='nav-links'>");
        out.println("<a href='./home'>Home</a>");
        out.println(Cohort12Framework.generateMenuItem());
        out.println("</div>");
        out.println("</div>");

        /* CONTENT */
        out.println(request.getAttribute(PageContent.CONTENT.name()));

        out.println("</body>");
        out.println("</html>");
    }
}
