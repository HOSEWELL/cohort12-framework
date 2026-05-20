package app.action;

import app.bean.TraineeBean;
import app.framework.*;
import app.model.Trainee;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@Action(value = "trainee", label = "Registered Trainees", linkPosition = 20)
public class TraineeAction {

    @Inject
    protected Cohort12Framework framework;

    @EJB
    private TraineeBean traineeBean;

    @ActionGetMethod("add")
    public ActionResponse add() {
        return new ActionResponse(framework.htmlForm(Trainee.class));
    }

    @ActionPostMethod("save")
    public ActionResponse save(@ActionRequestBody Trainee trainee) {
        traineeBean.save(trainee);
        return new ActionResponse(Trainee.class, this.list().getResponseDataList());
    }

    @ActionGetMethod("delete/{id}")
    public ActionResponse delete(@ActionPathParam("id") Long id) {
        traineeBean.delete(id);
        return new ActionResponse(Trainee.class, this.list().getResponseDataList());
    }

    @ActionGetMethod("list")
    public ActionResponse list() {
        return new ActionResponse(Trainee.class, traineeBean.list(new Trainee()));
    }

}
