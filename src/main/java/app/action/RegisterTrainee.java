package app.action;

import app.model.Trainee;
import app.utility.validation.TraineeQualifier;
import app.utility.validation.Validate;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register_trainee")
public class RegisterTrainee extends BaseAction<Trainee> {

    @Inject
    @TraineeQualifier
    public Validate validate;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        Trainee trainee = super.serializeForm(req.getParameterMap());
        if (validate.name(trainee.getName()))
            super.doPost(req, resp);
        else
            resp.sendRedirect("./trainee_lists");

    }
}
