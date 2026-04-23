package app.model;

import app.framework.*;

import java.io.Serializable;

@PageMenuItem(label = "Registered Schools", url = "./school_lists")
@DbTable(name = "schools")
@Cohort12Form(label = "Register School",
    actionUrl = "./register_school")
@Cohort12Table(label = "Schools",
    tableUrl = "./school_lists",
    registerUrl = "./register_school")
public class School implements Serializable {

    @DbColumn(name = "id", type = "INT", primaryKey = true, autoIncrement = true)
    private int id;

    @DbColumn(name = "school_name", type = "VARCHAR(255)")
    @Cohort12FormField(label = "School Name",
            name = "schoolName",
            placeholder = "Please enter School Name")
    @Cohort12TableCol(label = "School Names")
    private String schoolName;

    @DbColumn(name = "school_location", type = "VARCHAR(255)")
    @Cohort12FormField(label = "School Location",
            placeholder = "Please enter School Location")
    @Cohort12TableCol(label = "School Location")
    private String schoolLocation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolLocation() {
        return schoolLocation;
    }

    public void setSchoolLocation(String schoolLocation) {
        this.schoolLocation = schoolLocation;
    }
}
