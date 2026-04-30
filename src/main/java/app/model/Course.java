package app.model;

import app.framework.*;

import java.io.Serializable;

@PageMenuItem(label = "Registered Courses", url = "./course_lists")
@DbTable(name = "courses")
@Cohort12Form(label = "Course Register",
        actionUrl = "./register_course")
@Cohort12Table(label = "Courses",
        tableUrl = "./course_lists",
        registerUrl = "./register_course")
public class Course implements Serializable {

    @DbColumn(name = "id", type = "INT", primaryKey = true, autoIncrement = true)
    private int id;

    @DbColumn(name = "name", type = "VARCHAR(255)")
    @Cohort12FormField(label = "Course Name",
            placeholder = "Please enter Course Name")
    @Cohort12TableCol(label = "Course Name")
    private String name;

    @Cohort12FormField(label = "School Name",
        placeholder = "Please enter School Name")
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
