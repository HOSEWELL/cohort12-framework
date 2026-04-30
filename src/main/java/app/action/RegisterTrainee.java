package app.action;

import app.bean.SchoolBean;
import app.bean.TraineeBean;
import app.dao.TraineeDao;
import app.dao.GenericDao;
import app.model.Trainee;
import app.utility.general.TrainingApplication;
import app.utility.validation.Validate;
import app.utility.validation.ValidatorQualifier;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register_trainee")
public class RegisterTrainee extends BaseAction<Trainee> {

    @EJB
    private TraineeBean traineeBean;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (traineeBean.save(super.serializeForm(req.getParameterMap())))
            super.doPost(req, resp);
        else
            resp.sendRedirect("./trainee_lists");

    }
}
