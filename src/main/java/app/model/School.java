package app.model;

import app.framework.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


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

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Campus> campuses = new ArrayList<>();

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();

    @Embedded
    private Address address;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

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

    @JsonIgnore
    public List<Campus> getCampuses() {
        return campuses;
    }

    public void setCampuses(List<Campus> campuses) {
        this.campuses = campuses;
    }

    public void addCampus(Campus campus){
        campus.setSchool(this);
        campuses.add(campus);
    }

    public void addCampuses(List<Campus> campuses){
        campuses.forEach(this::addCampus);
    }

    @JsonIgnore
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course){
        course.setSchool(this);
        courses.add(course);
    }

    public void addCourses(List<Course> courses){
        courses.forEach(this::addCourse);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}