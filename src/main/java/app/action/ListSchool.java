package app.action;

import app.bean.SchoolBean;
import app.framework.PageContent;
import app.model.School;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/school_lists")
public class ListSchool extends BaseAction<School> {

    @EJB
    private SchoolBean schoolBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute(PageContent.CONTENT.name(),
                super.framework.htmlTable(getType(),
                        schoolBean.list(new School())));
        RequestDispatcher rd = request.getRequestDispatcher("./app_page");
        rd.include(request, response);
    }
}
