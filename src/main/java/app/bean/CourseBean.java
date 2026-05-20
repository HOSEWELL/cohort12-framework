package app.bean;

import app.dao.CourseDao;
import app.model.AuditTrail;
import app.model.Course;
import app.model.School;
import app.utility.validation.Validate;
import jakarta.ejb.Stateless;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Stateless
public class CourseBean {

    @Inject
    private Event<AuditTrail> auditTrailEvent;

    @Inject
    private CourseDao courseDao;

    @Inject
    @Named("ValidCourse")
    public Validate<Course> validate;

    public Course save(Course course){
        if (validate.process(course)) {
            if (course.getSchoolId() != null)
                course.setSchool(courseDao.getEm()
                    .getReference(School.class, course.getSchoolId()));

            auditTrailEvent.fire(new AuditTrail("Creating course: "
                + course.getName()));

            course = courseDao.save(course);
        }

        return course;
    }

    public boolean delete(Long id){
        if (id > 0 ) {
            auditTrailEvent.fire(new AuditTrail("Course Deleted, ID: " + id));
            courseDao.delete(id);
            return true;
        }

        return false;
    }

    public List<Course> list(Course filter){
        return courseDao.findAll();
    }

}
