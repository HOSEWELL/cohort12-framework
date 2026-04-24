package app.action;

import app.model.Trainer;
import app.utility.validation.TrainerQualifier;
import app.utility.validation.Validate;
import app.utility.validation.ValidateTrainee;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Register Trainer",
        urlPatterns = { "/register_trainer" },
        initParams = {
                @WebInitParam(name = "pageName", value = "Register - Training Academy"),
                @WebInitParam(name = "pageHeader", value = "Training Registration - IT")
        })
public class RegisterTrainer extends BaseAction<Trainer> {

        @Inject
        @TrainerQualifier
        public Validate validate;

        public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                Trainer trainer = super.serializeForm(req.getParameterMap());
                if (validate.name(trainer.getName()))
                        super.doPost(req, resp);
                else
                        resp.sendRedirect("./trainer_lists");

        }
}
