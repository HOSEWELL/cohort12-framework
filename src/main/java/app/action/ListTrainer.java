package app.action;

import app.bean.TrainerBean;
import app.framework.PageContent;
import app.model.Trainer;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/trainer_lists")
public class ListTrainer extends  BaseAction<Trainer> {

    @EJB
    private TrainerBean trainerBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute(PageContent.CONTENT.name(),
                super.framework.htmlTable(getType(),
                        trainerBean.list(new Trainer())));
        RequestDispatcher rd = request.getRequestDispatcher("./app_page");
        rd.include(request, response);
    }

}

