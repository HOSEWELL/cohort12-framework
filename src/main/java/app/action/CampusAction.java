package app.action;

import app.bean.CampusBean;
import app.bean.SchoolBean;
import app.framework.*;
import app.model.Campus;
import app.model.School;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Action(value = Campus.DOMAIN_NAME, label = "Registered Campus", linkPosition = 1)
public class CampusAction {

    @Inject
    protected Cohort12Framework framework;

    @EJB
    private CampusBean campusBean;

    @EJB
    private SchoolBean schoolBean;

    @ActionGetMethod("add")
    public ActionResponse add() {
        List<SelectBox> schoolSelections = new ArrayList<>();
        schoolBean.list(new School()).forEach(school -> schoolSelections.add(SelectBox.builder()
            .value(school.getId()+"")
            .name(school.getSchoolName())
            .build()));

        framework.getFormSelections().put("school", schoolSelections);

        return new ActionResponse(framework.htmlForm(Campus.class));
    }

    @ActionPostMethod("save")
    public ActionResponse save(@ActionRequestBody Campus campus) {

        campusBean.save(campus);
        return new ActionResponse(Campus.class, this.list().getResponseDataList());
    }

    @ActionGetMethod("delete/{id}")
    public ActionResponse delete(@ActionPathParam("id") Long id) {
        campusBean.delete(id);
        return new ActionResponse(Campus.class, this.list().getResponseDataList());
    }

    @ActionGetMethod("list")
    public ActionResponse list() {
        return new ActionResponse(Campus.class, campusBean.list(new Campus()));
    }

}
