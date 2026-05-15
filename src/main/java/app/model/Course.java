package app.model;

import app.framework.*;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "courses")
@Cohort12Form(label = "Course Register", actionUrl = "course/save")
@Cohort12Table(label = "Courses", addLink = "course/add", deleteLink = "course/delete")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    @Cohort12FormField(label = "Course Name",
        placeholder = "Please enter Course Name")
    @Cohort12TableCol(label = "Course Name")
    private String name;

    @Column(name = "school_name")
    @Cohort12FormField(label = "School Name",
        placeholder = "Please enter School Name", select = "school")
    private String schoolName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
