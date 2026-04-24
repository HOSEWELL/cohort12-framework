package app.action;

import app.model.Course;
import app.utility.validation.Validate;
import app.utility.validation.ValidatorQualifier;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register_course")
public class RegisterCourse extends BaseAction<Course> {

    @Inject
    @ValidatorQualifier(ValidatorQualifier.ValidationChoice.COURSE)
    public Validate validate;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Course course = super.serializeForm(req.getParameterMap());
        if (validate.name(course.getName()))
            super.doPost(req, resp);
        else
            resp.sendRedirect("./course_lists");

    }
}
