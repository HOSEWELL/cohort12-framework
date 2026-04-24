package app.action;

import app.model.School;
import app.utility.validation.Validate;
import app.utility.validation.ValidatorQualifier;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Register School",
    urlPatterns = { "/register_school" },
    initParams = {
            @WebInitParam(name = "pageName", value = "Register - Training Academy"),
            @WebInitParam(name = "pageHeader", value = "Training Registration - IT")
    })
public class RegisterSchool extends BaseAction<School> {

    @Inject
    @ValidatorQualifier(ValidatorQualifier.ValidationChoice.SCHOOL)
    public Validate validate;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        School school = super.serializeForm(req.getParameterMap());
        if (validate.name(school.getSchoolName()))
            super.doPost(req, resp);
        else
            resp.sendRedirect("./school_lists");

    }
}
