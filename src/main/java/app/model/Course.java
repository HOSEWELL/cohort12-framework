package app.model;

import app.framework.*;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "courses",
indexes = {
    @Index(name = "courses_course_category", columnList = "course_category")
})
@Cohort12Form(label = "Course Register", actionUrl = "course/save")
@Cohort12Table(label = "Courses", addLink = "course/add", deleteLink = "course/delete")
public class Course extends BaseEntity {

    @Column(name = "name", nullable = false)
    @Cohort12FormField(label = "Course Name",
        placeholder = "Please enter Course Name")
    @Cohort12TableCol(label = "Course Name")
    private String name;

    @Transient
    @Cohort12FormField(label = "School",
        placeholder = "Please select school",
        select = "school")
    private Long schoolId;

    @ManyToOne(fetch = FetchType.LAZY)
    private School school;

    @Cohort12TableCol(label = "School")
    @Formula("(select s.school_name from schools s " +
        "where s.id=school_id)")
    private String schoolName;

    @Column(name = "course_category")
    private String courseCategory;


    public Course(){}

    public Course(String name){ this.name = name;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }
}
