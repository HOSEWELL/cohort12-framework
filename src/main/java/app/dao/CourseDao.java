package app.dao;

import app.model.Course;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CourseDao extends GenericDao<Course, Long> {

}
