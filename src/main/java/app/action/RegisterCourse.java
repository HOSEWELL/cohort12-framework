package app.action;

import app.model.Course;
import app.utility.validation.Validate;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register_course")
public class RegisterCourse extends BaseAction<Course> {

    @Inject
    @Named("ValidCourse")
    public Validate<Course> validate;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Course course = super.serializeForm(req.getParameterMap());
        if (validate.process(course))
            super.doPost(req, resp);
        else
            resp.sendRedirect("./course_lists");

    }
}
