package app.action;

import app.bean.TraineeBean;
import app.model.Trainee;
import jakarta.ejb.EJB;
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
