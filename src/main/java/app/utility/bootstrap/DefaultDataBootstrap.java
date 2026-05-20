package app.utility.bootstrap;

import app.bean.SchoolBean;
import app.model.Campus;
import app.model.Course;
import app.model.School;
import app.model.Status;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.Dependent;

import java.util.ArrayList;
import java.util.List;

@InitBootstrap
@Dependent

public class DefaultDataBootstrap implements Bootstrap {

    @EJB
    private SchoolBean schoolBean;

    @Override
    public void process() {

        /*School defaultSchool = new School();
        defaultSchool.setSchoolName("Kenya School Of Government");
        defaultSchool.setSchoolLocation("NAIROBI, Kabete");
        defaultSchool.setStatus(Status.ACTIVE);

        List<Campus> defaultCampuses = new ArrayList<>();
        defaultCampuses.add(new Campus("Bungoma"));
        defaultCampuses.add(new Campus("Eldoret"));
        defaultCampuses.add(new Campus("Machakos"));
        defaultCampuses.add(new Campus("Kilifi"));

        defaultSchool.addCampuses(defaultCampuses);


        List<Course> defaultCourses = new ArrayList<>();
        defaultCourses.add(new Course("Computer Science"));
        defaultCourses.add(new Course("Software Engineering"));
        defaultCourses.add(new Course("Food Security"));
        defaultCourses.add(new Course("Environment Engineering"));

        defaultSchool.addCourses(defaultCourses);

        schoolBean.save(defaultSchool);*/

        schoolBean.list(new School()).forEach(school -> {
            school.setStatus(Status.ACTIVE);
            schoolBean.save(school);
        });

    }
}
