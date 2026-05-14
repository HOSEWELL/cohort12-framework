package app.model;

import app.framework.*;

import java.io.Serializable;

@DbTable(name = "campuses")
@Cohort12Form(label = "Register Campus", actionUrl = Campus.DOMAIN_NAME + "/save")
@Cohort12Table(label = "Campus", addLink = Campus.DOMAIN_NAME + "/add", deleteLink = Campus.DOMAIN_NAME + "/delete")
public class Campus implements Serializable {

    public static final String DOMAIN_NAME = "campus";

    @DbColumn(name = "id", type = "INT", primaryKey = true, autoIncrement = true)
    private int id;

    @DbColumn(name = "name", type = "VARCHAR(255)")
    @Cohort12FormField(label = "Campus Name",
            placeholder = "Please enter campus Name")
    @Cohort12TableCol(label = "Campus Name")
    private String name;

    @DbColumn(name = "school_name", type = "VARCHAR(255)")
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
