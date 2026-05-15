package app.model;

import app.framework.*;
import jakarta.persistence.*;


@Entity
@Table(name = "schools")
@Cohort12Form(label = "Register School", actionUrl = "school/save")
@Cohort12Table(label = "Schools", addLink = "school/add", deleteLink = "school/delete")
public class School extends BaseEntity {

    @Column(name = "school_name", nullable = false)
    @Cohort12FormField(label = "School Name",
        name = "schoolName",
        placeholder = "Please enter School Name")
    @Cohort12TableCol(label = "School Names")
    private String schoolName;

    @Column(name = "school_location")
    @Cohort12FormField(label = "School Location",
        placeholder = "Please enter School Location")
    @Cohort12TableCol(label = "School Location")
    private String schoolLocation;

    public School(){}

    public School(String schoolName){
        this.schoolName = schoolName;
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
