package app.model;

import app.framework.*;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;


@Entity
@Table(name = "campuses")
@Cohort12Form(label = "Register Campus", actionUrl = Campus.DOMAIN_NAME + "/save")
@Cohort12Table(label = "Campus", addLink = Campus.DOMAIN_NAME + "/add", deleteLink = Campus.DOMAIN_NAME + "/delete")
public class Campus extends BaseEntity {

    public static final String DOMAIN_NAME = "campus";

    @Column(nullable = false)
    @Cohort12FormField(label = "Campus Name",
        placeholder = "Please enter campus Name")
    @Cohort12TableCol(label = "Campus Name")
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

    @Embedded
    private Address address;

    public Campus(){}

    public Campus(String name){ this.name = name;}

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
