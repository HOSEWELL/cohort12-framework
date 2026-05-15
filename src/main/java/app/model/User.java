package app.model;

import app.framework.Cohort12Form;
import app.framework.Cohort12FormField;
import app.framework.Cohort12Table;
import app.framework.Cohort12TableCol;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@Cohort12Form(label = "Register User",
    actionUrl = User.DOMAIN_NAME + "/save")
@Cohort12Table(label = "User",
    addLink = User.DOMAIN_NAME + "/add",
    deleteLink = User.DOMAIN_NAME + "/delete")
public class User extends BaseEntity {

    public static final String DOMAIN_NAME = "user";

    @Column(nullable = false)
    @Cohort12FormField(label = "First Name",
            placeholder = "Please enter first name")
    @Cohort12TableCol(label = "First Name")
    private String firstName;

    @Column(nullable = false)
    @Cohort12FormField(label = "Last Name",
            placeholder = "Please enter last name")
    @Cohort12TableCol(label = "Last Name")
    private String latName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLatName() {
        return latName;
    }

    public void setLatName(String latName) {
        this.latName = latName;
    }
}
