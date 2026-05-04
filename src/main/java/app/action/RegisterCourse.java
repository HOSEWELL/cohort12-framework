package app.action;

import app.bean.CourseBean;
import app.model.Course;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register_course")
public class RegisterCourse extends BaseAction<Course> {

    @EJB
    private CourseBean courseBean;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (courseBean.save(super.serializeForm(req.getParameterMap())))
            super.doPost(req, resp);
        else
            resp.sendRedirect("./course_lists");

    }
}
