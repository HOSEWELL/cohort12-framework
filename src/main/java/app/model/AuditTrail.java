package app.model;


import app.framework.*;

import java.io.Serializable;

@PageMenuItem(label = "Audit Trail", url = "./audit_trail_lists")
@DbTable(name = "audit_trail")
@Cohort12Table(label = "Audit Trail",
    tableUrl = "./audit_trail_lists",
    registerUrl = "./")
public class AuditTrail implements Serializable {

    @DbColumn(name = "id", type = "INT", primaryKey = true, autoIncrement = true)
    private int id;

    @DbColumn(name = "activities", type = "TEXT")
    @Cohort12TableCol(label = "Activity Details")
    private String activity;

    public AuditTrail(){}

    public AuditTrail(String activity){
        this.activity = activity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
