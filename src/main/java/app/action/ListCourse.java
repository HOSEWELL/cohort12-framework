package app.action;

import app.bean.CourseBean;
import app.dao.CourseDao;
import app.dao.GenericDao;
import app.framework.PageContent;
import app.model.Course;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/course_lists")
public class ListCourse extends BaseAction<Course> {

    @EJB
    private CourseBean courseBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute(PageContent.CONTENT.name(),
            super.framework.htmlTable(getType(),
            courseBean.list(new Course())));
        RequestDispatcher rd = request.getRequestDispatcher("./app_page");
        rd.include(request, response);
    }

}
