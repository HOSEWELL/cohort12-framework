package app.model;

import app.framework.*;

@PageMenuItem(label = "Registered Trainers", url = "./trainer_lists")
@DbTable(name = "trainers")
@Cohort12Form(label = "Trainer Register",
        actionUrl = "./register_trainer")
@Cohort12Table(label = "Trainers",
        tableUrl = "./trainer_lists",
        registerUrl = "./register_trainer")
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
            placeholder = "Please enter  Gender")
    @Cohort12TableCol(label = "Trainer Gender")
    private String gender;

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
}
