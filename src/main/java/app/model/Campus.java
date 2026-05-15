package app.model;

import app.framework.*;
import jakarta.persistence.*;

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

    @Transient
    @Cohort12TableCol(label = "School")
    private String schoolName;

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
}
