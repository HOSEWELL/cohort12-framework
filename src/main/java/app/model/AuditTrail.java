package app.model;


import app.framework.*;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "audit_trail")
@Cohort12Table(label = "Audit Trail")
public class AuditTrail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "activities", columnDefinition = "TEXT")
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
