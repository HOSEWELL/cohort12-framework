package app.model;

import app.framework.*;

@DbTable(name = "trainers")
@Cohort12Form(label = "Trainer Register", actionUrl = "trainer/save")
@Cohort12Table(label = "Trainers",  addLink = "trainer/add", deleteLink = "trainer/delete")
public class Trainer{

    @DbColumn(name = "id", type = "INT", primaryKey = true, autoIncrement = true)
    private int id;

    @DbColumn(name = "name", type = "VARCHAR(255)")
    @Cohort12FormField(label = "Trainer Name",
        placeholder = "Please enter Name")
    @Cohort12TableCol(label = "Trainer Name")
    private String name;

    @DbColumn(name = "genders", type = "VARCHAR(255)")
    @Cohort12FormField(label = "Trainer Gender",
        placeholder = "Please enter  Gender", select = "gender")
    @Cohort12TableCol(label = "Trainer Gender")
    private String gender;

    @DbColumn(name = "phone_number", type = "VARCHAR(255)")
    @Cohort12FormField(label = "Trainer Phone #",
        placeholder = "Please enter  Phone #")
    @Cohort12TableCol(label = "Phone #")
    private String phoneNumber;

    @DbColumn(name = "email_address", type = "VARCHAR(255)")
    @Cohort12FormField(label = "Trainer Email Address",
        placeholder = "Please enter  Email Address")
    @Cohort12TableCol(label = "Phone #")
    private String emailAddress;

    @DbColumn(name = "qualification", type = "TEXT")
    @Cohort12FormField(label = "Trainer Qualifications",
        placeholder = "Please enter Qualifications")
    @Cohort12TableCol(label = "Phone #")
    private String qualification;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
