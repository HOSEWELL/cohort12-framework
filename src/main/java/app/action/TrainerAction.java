package app.action;

import app.bean.TrainerBean;
import app.framework.*;
import app.model.Trainer;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@Action(value = "trainer", label = "Registered Trainers", linkPosition = 10)
public class TrainerAction  {

    @Inject
    protected Cohort12Framework framework;

    @EJB
    private TrainerBean trainerBean;

    @ActionGetMethod("add")
    public ActionResponse add() {
        return new ActionResponse(framework.htmlForm(Trainer.class));

    }

    @ActionPostMethod("save")
    public ActionResponse save(@ActionRequestBody Trainer trainer) {
        trainerBean.save(trainer);
        return new ActionResponse(Trainer.class, this.list().getResponseDataList());
    }

    @ActionGetMethod("delete/{id}")
    public ActionResponse delete(@ActionPathParam("id") Long id) {
        trainerBean.delete(id);
        return new ActionResponse(Trainer.class, this.list().getResponseDataList());
    }

    @ActionGetMethod("list")
    public ActionResponse list() {
        return new ActionResponse(Trainer.class, trainerBean.list(new Trainer()));
    }

}
