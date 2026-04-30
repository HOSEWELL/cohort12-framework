package app.action;

import app.bean.CourseBean;
import app.bean.SchoolBean;
import app.dao.SchoolDao;
import app.dao.GenericDao;
import app.model.School;
import app.utility.validation.Validate;
import app.utility.validation.ValidatorQualifier;
import jakarta.ejb.EJB;
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

    @EJB
    private SchoolBean schoolBean;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (schoolBean.save(super.serializeForm(req.getParameterMap())))
            super.doPost(req, resp);
        else
            resp.sendRedirect("./school_lists");

    }
}
