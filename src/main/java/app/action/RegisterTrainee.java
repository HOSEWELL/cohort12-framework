package app.action;

import app.model.Trainee;
import app.utility.general.TrainingApplication;
import app.utility.validation.Validate;
import app.utility.validation.ValidatorQualifier;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register_trainee")
public class RegisterTrainee extends BaseAction<Trainee> {

    @Inject
    @ValidatorQualifier(ValidatorQualifier.ValidationChoice.TRAINEE)
    public Validate<Trainee> validate;

    @Inject
    public TrainingApplication trainingApplication;

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{

        Trainee trainee = super.serializeForm(req.getParameterMap());
        if (validate.process(trainee)
            && trainingApplication.exists(trainee.getNationalId()))
            super.doPost(req, resp);
        else
            resp.sendRedirect("./trainee_lists");

    }
}
