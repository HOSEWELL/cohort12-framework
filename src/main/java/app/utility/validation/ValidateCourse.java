package app.utility.validation;

import app.bean.SchoolBean;
import app.model.Course;
import app.model.School;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named("ValidCourse")
@ApplicationScoped
public class ValidateCourse implements Validate<Course> {

    @EJB
    private SchoolBean schoolBean;

    @Override
    public void printValidation() {
        System.out.println("Validate Course");
    }

    @Override
    public boolean process(Course course) {
        return course != null
            && course.getName() != null
            && course.getName().length() > 5
            && schoolBean.exist(new School(course.getSchoolName()));
    }
}
