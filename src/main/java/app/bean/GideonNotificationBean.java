package app.bean;

import app.model.AuditTrail;
import jakarta.ejb.Singleton;
import jakarta.enterprise.event.Observes;

@Singleton
public class GideonNotificationBean {

    public void notification(@Observes AuditTrail auditTrail){
        System.out.println("There audit trail " +
            "and Gideon need to make call to Mike");
    }
}
