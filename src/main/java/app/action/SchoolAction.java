package app.action;

import app.bean.SchoolBean;
import app.framework.*;
import app.model.School;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@Action(value = "school", label = "Registered Schools")
public class SchoolAction {

    @Inject
    protected Cohort12Framework framework;

    @EJB
    private SchoolBean schoolBean;

    @ActionGetMethod("add")
    public ActionResponse add() {
        return new ActionResponse(framework.htmlForm(School.class));
    }

    @ActionPostMethod("save")
    public ActionResponse save(@ActionRequestBody School school) {
        schoolBean.save(school);
        return new ActionResponse(School.class, this.list().getResponseDataList());
    }

    @ActionGetMethod("delete/{id}")
    public ActionResponse delete(@ActionPathParam("id") Long id) {
        schoolBean.delete(id);
        return new ActionResponse(School.class, this.list().getResponseDataList());
    }

    @ActionGetMethod("list")
    public ActionResponse list() {
        return new ActionResponse(School.class, schoolBean.list(new School()));
    }

    @ActionPostMethod("update_status")
    public ActionResponse updateStatus(@ActionRequestBody School school) {
        try {
            schoolBean.updateStatus(school);
            return new ActionResponse(School.class, this.list().getResponseDataList());
        } catch (Exception e) {
            return new ActionResponse(e.getMessage());
        }

    }

}
