package app.model;


import app.framework.*;
import jakarta.persistence.*;

@Entity
@Table(name = "audit_trail")
@Cohort12Table(label = "Audit Trail")
public class AuditTrail extends BaseEntity {

    @Column(name = "activities", columnDefinition = "TEXT")
    @Cohort12TableCol(label = "Activity Details")
    private String activity;

    public AuditTrail(){}

    public AuditTrail(String activity){
        this.activity = activity;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
