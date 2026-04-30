package app.action;

import app.bean.TraineeBean;
import app.bean.TrainerBean;
import app.dao.TrainerDao;
import app.dao.GenericDao;
import app.model.Trainer;
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

@WebServlet(name = "Register Trainer",
        urlPatterns = { "/register_trainer" },
        initParams = {
                @WebInitParam(name = "pageName", value = "Register - Training Academy"),
                @WebInitParam(name = "pageHeader", value = "Training Registration - IT")
        })
public class RegisterTrainer extends BaseAction<Trainer> {

        @EJB
        private TrainerBean trainerBean;

        public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                if (trainerBean.save(super.serializeForm(req.getParameterMap())))
                        super.doPost(req, resp);
                else
                        resp.sendRedirect("./trainer_lists");

        }
}
