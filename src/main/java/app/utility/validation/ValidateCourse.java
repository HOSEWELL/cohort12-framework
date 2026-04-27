package app.utility.validation;

import app.model.Course;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named("ValidCourse")
@ApplicationScoped
public class ValidateCourse implements Validate<Course> {

    @Override
    public void printValidation() {
        System.out.println("Validate Course");
    }

    @Override
    public boolean process(Course course) {
        return course != null
            && course.getName() != null
            && course.getName().length() > 5;
    }
}
