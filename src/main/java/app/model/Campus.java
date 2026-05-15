package app.model;

import app.framework.*;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "campuses")
@Cohort12Form(label = "Register Campus", actionUrl = Campus.DOMAIN_NAME + "/save")
@Cohort12Table(label = "Campus", addLink = Campus.DOMAIN_NAME + "/add", deleteLink = Campus.DOMAIN_NAME + "/delete")
public class Campus implements Serializable {

    public static final String DOMAIN_NAME = "campus";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Cohort12FormField(label = "Campus Name",
        placeholder = "Please enter campus Name")
    @Cohort12TableCol(label = "Campus Name")
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
