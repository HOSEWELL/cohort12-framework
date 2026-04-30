package app.action;

import app.bean.CourseBean;
import app.dao.CourseDao;
import app.dao.GenericDao;
import app.model.Course;
import app.utility.validation.Validate;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.inject.Named;
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
