package app.model;

import app.framework.*;

import java.io.Serializable;

@DbTable(name = "trainees")
@Cohort12Form(label = "Trainee Register", actionUrl = "trainee/save")
@Cohort12Table(label = "Trainees", addLink = "trainee/add", deleteLink = "trainee/delete")
public class Trainee implements Serializable {

    @DbColumn(name = "id", type = "INT", primaryKey = true, autoIncrement = true)
    private int id;

    @DbColumn(name = "name", type = "VARCHAR(255)")
    @Cohort12FormField(label = "Full Name",
            placeholder = "Please enter Trainee Name")
    @Cohort12TableCol(label = "Trainee Name")
    private String name;

    @DbColumn(name = "national_id", type = "VARCHAR(100)")
    @Cohort12FormField(label = "National ID",
        placeholder = "Please enter ID")
    @Cohort12TableCol(label = "National ID")
    private String nationalId;

    @DbColumn(name = "genders", type = "VARCHAR(255)")
    @Cohort12FormField(label = "Trainee Gender",
            placeholder = "Please enter Gender", select = "gender")
    @Cohort12TableCol(label = "Trainee Gender")
    private String gender;

    @DbColumn(name = "phone_number", type = "VARCHAR(255)")
    @Cohort12FormField(label = "Phone #",
            placeholder = "Please enter Phone #")
    @Cohort12TableCol(label = "Phone #")
    private String phoneNumber;

    @DbColumn(name = "email_address", type = "VARCHAR(255)")
    @Cohort12FormField(label = "Email Address",
            placeholder = "Please enter Email Address")
    @Cohort12TableCol(label = "Phone #")
    private String emailAddress;

    @DbColumn(name = "address", type = "VARCHAR(255)")
    @Cohort12FormField(label = "Address",
            placeholder = "Please enter address")
    @Cohort12TableCol(label = "Phone #")
    private String address;

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

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
