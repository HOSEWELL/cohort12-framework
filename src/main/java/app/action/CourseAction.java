package app.action;

import app.bean.CourseBean;
import app.bean.SchoolBean;
import app.framework.*;
import app.model.Course;
import app.model.School;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Action(value = "course", label = "Registered Courses", linkPosition = 5)
public class CourseAction {

    @Inject
    protected Cohort12Framework framework;

    @EJB
    private CourseBean courseBean;

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

        return new ActionResponse(framework.htmlForm(Course.class));
    }

    @ActionPostMethod("save")
    public ActionResponse save(@ActionRequestBody Course course) {
        courseBean.save(course);
        return new ActionResponse(Course.class, this.list().getResponseDataList());
    }

    @ActionGetMethod("delete/{id}")
    public ActionResponse delete(@ActionPathParam("id") Long id) {
        courseBean.delete(id);
        return new ActionResponse(Course.class, this.list().getResponseDataList());
    }

    @ActionGetMethod("list")
    public ActionResponse list() {
        return new ActionResponse(Course.class, courseBean.list(new Course()));
    }

    @ActionPostMethod("upload")
    public ActionResponse upload(@ActionRequestBody Course course){
        System.out.println("call ben to upload course!!");
        return new ActionResponse("Done!!");
    }

}
